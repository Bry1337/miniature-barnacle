package bry1337.github.io.deliveryman.presentation.home

import androidx.lifecycle.MutableLiveData
import bry1337.github.io.deliveryman.BaseViewModel
import bry1337.github.io.deliveryman.model.Delivery
import bry1337.github.io.deliveryman.model.Location

/**
 * Created by edwardbryan.abergas on 08/07/2019.
 *
 * @author edwardbryan.abergas@gmail.com
 */
class DeliveryViewModel : BaseViewModel() {
  private val id = MutableLiveData<Int>()
  private val description = MutableLiveData<String>()
  private val location = MutableLiveData<Location>()

  fun bind(delivery: Delivery) {
    id.value = delivery.id
    description.value = delivery.description
    location.value = delivery.location
  }

  fun getDescription(): MutableLiveData<String> {
    return description
  }

}