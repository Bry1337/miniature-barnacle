package bry1337.github.io.deliveryman.presentation.location

import androidx.lifecycle.MutableLiveData
import bry1337.github.io.deliveryman.BaseViewModel
import bry1337.github.io.deliveryman.model.Delivery

/**
 * Created by edwardbryan.abergas on 08/07/2019.
 *
 * @author edwardbryan.abergas@gmail.com
 */
class LocationViewModel: BaseViewModel() {


  val description: MutableLiveData<String> = MutableLiveData()
  val imageUrl: MutableLiveData<String> = MutableLiveData()

  fun setDelivery(delivery: Delivery){
    description.value = delivery.description
    imageUrl.value = delivery.imageUrl
  }
}