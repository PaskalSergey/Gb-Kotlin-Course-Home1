package gb.kotlin_course_home_1.model.dto


import com.google.gson.annotations.SerializedName

data class Tzinfo(
    @SerializedName("abbr")
    val abbr: String,
    @SerializedName("dst")
    val dst: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("offset")
    val offset: Int
)