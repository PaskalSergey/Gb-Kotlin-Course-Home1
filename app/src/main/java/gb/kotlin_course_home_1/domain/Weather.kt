package gb.kotlin_course_home_1.domain

data class Weather(
    val city: City = getDefaultCity(),
    val temperature: Int = 35,
    val kindOfWeather: String = "Облачно с прояснениями",
    val feelsLike: Int = 30

)

data class City(
    val name: String
)

fun getDefaultCity() = City("Донецк")
