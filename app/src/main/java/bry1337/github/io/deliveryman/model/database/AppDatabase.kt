package bry1337.github.io.deliveryman.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import bry1337.github.io.deliveryman.model.Delivery
import bry1337.github.io.deliveryman.model.dao.DeliveryDao
import bry1337.github.io.deliveryman.util.LocationConverter

/**
 * Created by edwardbryan.abergas on 08/07/2019.
 *
 * @author edwardbryan.abergas@gmail.com
 */
@Database(entities = [(Delivery::class)], version = 1, exportSchema = false)
@TypeConverters(LocationConverter::class)
abstract class AppDatabase: RoomDatabase() {
  abstract fun deliveryDao(): DeliveryDao
}