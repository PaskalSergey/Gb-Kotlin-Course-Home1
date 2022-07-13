package gb.kotlin_course_home_1.model.dto


import com.google.gson.annotations.SerializedName

data class Yesterday(
    @SerializedName("temp")
    val temp: Int
)