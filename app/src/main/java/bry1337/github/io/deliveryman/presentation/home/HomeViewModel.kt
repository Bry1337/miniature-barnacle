package bry1337.github.io.deliveryman.presentation.home

import android.view.View
import androidx.lifecycle.MutableLiveData
import bry1337.github.io.deliveryman.BaseViewModel
import bry1337.github.io.deliveryman.model.Delivery
import bry1337.github.io.deliveryman.model.dao.DeliveryDao
import bry1337.github.io.deliveryman.network.DeliveryApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by edwardbryan.abergas on 08/07/2019.
 *
 * @author edwardbryan.abergas@gmail.com
 */
class HomeViewModel(private val deliveryDao: DeliveryDao) : BaseViewModel() {

  @Inject
  lateinit var deliveryApi: DeliveryApi

  val deliveryListAdapter: DeliveryListAdapter = DeliveryListAdapter()
  val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
  val errorMessage: MutableLiveData<String> = MutableLiveData()

  private lateinit var subscription: Disposable

  override fun onCleared() {
    super.onCleared()
    if (::subscription.isInitialized) {
      subscription.dispose()
    }
  }

  init {
    loadDeliveries()
  }

  private fun loadDeliveries() {
    subscription = Observable.fromCallable { deliveryDao.all }
        .concatMap { result ->
          if (result.isEmpty()) {
              deliveryApi.getDeliveries().concatMap { deliveryList ->
              deliveryDao.insertAll(deliveryList)
              Observable.just(deliveryList)
            }
          } else {
            Observable.just(result)
          }
        }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe { loadingVisibility.value = View.VISIBLE }
        .doOnTerminate { loadingVisibility.value = View.GONE }
        .subscribe({ deliveryList ->
          onRetrieveDeliverySuccess(deliveryList)
        }, {throwable ->
          onRetrieveDeliveryError(throwable.localizedMessage)
        })
  }

  private fun onRetrieveDeliverySuccess(deliveryList: List<Delivery>) {
    deliveryListAdapter.updateDeliveryList(deliveryList)
  }

  private fun onRetrieveDeliveryError(localizedMessage: String) {
    loadingVisibility.value = View.GONE
    errorMessage.value = localizedMessage
  }

  fun setClickListener(clickListener: DeliveryClickListener) {
    deliveryListAdapter.setDeliveryClickListener(clickListener)
  }
}