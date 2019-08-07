package bry1337.github.io.deliveryman.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import bry1337.github.io.deliveryman.util.LocationConverter
import com.google.gson.annotations.SerializedName
import org.parceler.Parcel

/**
 * Created by edwardbryan.abergas on 08/07/2019.
 *
 * @author edwardbryan.abergas@gmail.com
 */
@Entity
data class Delivery(@PrimaryKey var id: Int): Parcelable {

  @SerializedName("description")
  var description: String? = null

  @SerializedName("imageUrl")
  var imageUrl: String? = null

  @TypeConverters(LocationConverter::class)
  @SerializedName("location")
  var location: Location = Location()

  constructor(parcel: android.os.Parcel) : this(parcel.readInt()) {
    description = parcel.readString()
    imageUrl = parcel.readString()
    location = parcel.readParcelable(Location::class.java.classLoader)
  }

  companion object CREATOR : Parcelable.Creator<Delivery> {
    override fun createFromParcel(parcel: android.os.Parcel): Delivery {
      return Delivery(parcel)
    }

    override fun newArray(size: Int): Array<Delivery?> {
      return arrayOfNulls(size)
    }
  }

  override fun writeToParcel(dest: android.os.Parcel?, flags: Int) {
    dest?.writeInt(this.id)
    dest?.writeString(this.description)
    dest?.writeString(this.imageUrl)
    dest?.writeParcelable(this.location, flags)
  }

  override fun describeContents(): Int {
    return 0
  }

}