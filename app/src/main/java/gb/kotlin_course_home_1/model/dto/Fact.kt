package gb.kotlin_course_home_1.model.dto


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Fact(
    @SerializedName("cloudness")
    val cloudness: Double,
    @SerializedName("condition")
    val condition: String,
    @SerializedName("daytime")
    val daytime: String,
    @SerializedName("feels_like")
    val feelsLike: Int,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("is_thunder")
    val isThunder: Boolean,
    @SerializedName("obs_time")
    val obsTime: Int,
    @SerializedName("polar")
    val polar: Boolean,
    @SerializedName("prec_prob")
    val precProb: Int,
    @SerializedName("prec_strength")
    val precStrength: Double,
    @SerializedName("prec_type")
    val precType: Int,
    @SerializedName("pressure_mm")
    val pressureMm: Int,
    @SerializedName("pressure_pa")
    val pressurePa: Int,
    @SerializedName("season")
    val season: String,
    @SerializedName("source")
    val source: String,
    @SerializedName("temp")
    val temp: Int,
    @SerializedName("temp_water")
    val tempWater: Int,
    @SerializedName("uptime")
    val uptime: Int,
    @SerializedName("uv_index")
    val uvIndex: Int,
    @SerializedName("wind_dir")
    val windDir: String,
    @SerializedName("wind_gust")
    val windGust: Double,
    @SerializedName("wind_speed")
    val windSpeed: Double
) : Parcelable