package gb.kotlin_course_home_1.model.dto


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Fact(
    @SerializedName("condition")
    val condition: String,
    @SerializedName("feels_like")
    val feelsLike: Int,
    @SerializedName("temp")
    val temp: Int,
) : Parcelable