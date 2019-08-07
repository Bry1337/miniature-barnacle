package bry1337.github.io.deliveryman.util

import androidx.room.TypeConverter
import bry1337.github.io.deliveryman.model.Location
import com.google.gson.Gson
import com.google.gson.JsonParser


/**
 * Created by edwardbryan.abergas on 08/07/2019.
 *
 * @author edwardbryan.abergas@gmail.com
 */
class LocationConverter {

  companion object {
    @TypeConverter
    @JvmStatic
    fun ToString(location: Location): String {
      var jsonString = Gson().toJson(location)
      return jsonString
    }

    @TypeConverter
    @JvmStatic
    fun ToLocation(location: String): Location{
      val mJson = JsonParser().parse(location)
      return Gson().fromJson(mJson, Location::class.java)
    }
  }
}