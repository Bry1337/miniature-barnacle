package bry1337.github.io.deliveryman.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by edwardbryan.abergas on 08/07/2019.
 *
 * @author edwardbryan.abergas@gmail.com
 */
class Location(): Parcelable, Serializable {

  @SerializedName("lat")
  var lat: Double? = null

  @SerializedName("lng")
  var lng: Double? = null

  @SerializedName("address")
  var address: String? = null

  constructor(parcel: android.os.Parcel) : this() {
    lat = parcel.readValue(Double::class.java.classLoader) as? Double
    lng = parcel.readValue(Double::class.java.classLoader) as? Double
    address = parcel.readString()
  }

  companion object CREATOR : Parcelable.Creator<Location> {
    override fun createFromParcel(parcel: android.os.Parcel): Location {
      return Location(parcel)
    }

    override fun newArray(size: Int): Array<Location?> {
      return arrayOfNulls(size)
    }
  }

  override fun writeToParcel(dest: android.os.Parcel?, flags: Int) {
    dest?.writeDouble(this.lat!!)
    dest?.writeDouble(this.lng!!)
    dest?.writeString(this.address!!)

  }

  override fun describeContents(): Int {
    return 0
  }
}