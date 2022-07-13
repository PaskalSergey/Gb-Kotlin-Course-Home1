package gb.kotlin_course_home_1.model.dto


import com.google.gson.annotations.SerializedName

data class Parts(
    @SerializedName("day")
    val day: Day,
    @SerializedName("day_short")
    val dayShort: DayShort,
    @SerializedName("evening")
    val evening: Evening,
    @SerializedName("morning")
    val morning: Morning,
    @SerializedName("night")
    val night: Night,
    @SerializedName("night_short")
    val nightShort: NightShort
)