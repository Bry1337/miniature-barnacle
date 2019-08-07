package bry1337.github.io.deliveryman.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import bry1337.github.io.deliveryman.model.Delivery

/**
 * Created by edwardbryan.abergas on 08/07/2019.
 *
 * @author edwardbryan.abergas@gmail.com
 */
@Dao
interface DeliveryDao {

  @get:Query("Select * from delivery")
  val all: List<Delivery>

  @Insert
  fun insertAll(deliveryList: ArrayList<Delivery>)

  @Query("Delete from delivery")
  fun deleteAll()
}