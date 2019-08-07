package bry1337.github.io.deliveryman.injection

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import bry1337.github.io.deliveryman.model.database.AppDatabase
import bry1337.github.io.deliveryman.presentation.home.HomeViewModel
import bry1337.github.io.deliveryman.presentation.location.LocationViewModel
import java.lang.IllegalArgumentException

/**
 * Created by edwardbryan.abergas on 08/07/2019.
 *
 * @author edwardbryan.abergas@gmail.com
 */
@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val activity: AppCompatActivity): ViewModelProvider.Factory {
  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    if(modelClass.isAssignableFrom(HomeViewModel::class.java)){
      val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "delivery").build()
      return HomeViewModel(db.deliveryDao()) as T
    }else if(modelClass.isAssignableFrom(LocationViewModel::class.java)){
      return LocationViewModel() as T
    }
    throw IllegalArgumentException("Unknown ViewModel class")
  }

}