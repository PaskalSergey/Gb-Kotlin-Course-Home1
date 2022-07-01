package gb.kotlin_course_home_1.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Weather(
    val city: City = getDefaultCity(),
    val temperature: Int = 35,
    val kindOfWeather: String = "Облачно с прояснениями",
    val feelsLike: Int = 30
) : Parcelable

@Parcelize
data class City(
    val name: String
) : Parcelable

fun getDefaultCity() = City("Донецк")

fun getWorldCity(): List<Weather> {
    return listOf(
        Weather(City("Лондон"), 32, "Солнечная", 35),
        Weather(City("Токио"), 31, "Пасмурная", 30),
        Weather(City("Париж"), 32, "Дождливая", 28),
        Weather(City("Берлин"), 25, "Дождливая", 20),
        Weather(City("Рим"), 27, "Жаркая", 30),
        Weather(City("Минск"), 27, "Облачная", 25),
        Weather(City("Стамбул"), 28, "Солнечная", 20),
        Weather(City("Вашингтон"), 30, "Облачно с прояснениями", 27),
        Weather(City("Киев"), 32, "Облачно с прояснениями", 32),
        Weather(City("Пекин"), 32, "Облачно с прояснениями", 30)
    )
}

fun getRussianCity(): List<Weather> {
    return listOf(
        Weather(City("Москва"), 32, "Солнечная", 35),
        Weather(City("Санкт-Петербург"), 31, "Пасмурная", 30),
        Weather(City("Новосибирск"), 32, "Дождливая", 28),
        Weather(City("Екатеринбург"), 25, "Дождливая", 20),
        Weather(City("Нижний Новгород"), 27, "Жаркая", 30),
        Weather(City("Казань"), 27, "Облачная", 25),
        Weather(City("Челябинск"), 28, "Солнечная", 20),
        Weather(City("Омск"), 30, "Облачно с прояснениями", 27),
        Weather(City("Ростов-на-Дону"), 32, "Облачно с прояснениями", 32),
        Weather(City("Уфа"), 32, "Облачно с прояснениями", 30)
    )
}

