package bry1337.github.io.deliveryman.network

import bry1337.github.io.deliveryman.model.Delivery
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by edwardbryan.abergas on 08/07/2019.
 *
 * @author edwardbryan.abergas@gmail.com
 */
interface DeliveryApi {

  @GET("/deliveries")
  fun getDeliveries(): Observable<ArrayList<Delivery>>

}