package gb.kotlin_course_home_1.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Weather(
    val city: City = getDefaultCity(),
    var temperature: Int = 35,
    var kindOfWeather: String = "Облачно с прояснениями",
    var feelsLike: Int = 30
) : Parcelable

@Parcelize
data class City(
    val name: String,
    val lat: Double,
    val lon: Double
) : Parcelable

fun getDefaultCity() = City("Донецк", 48.015883, 37.80285)

fun getWorldCity() = listOf(
    Weather(City("Лондон", 59.0034, 13.9824), 32, "Солнечная", 35),
    Weather(City("Токио", 35.6895, 139.6917), 31, "Пасмурная", 30),
    Weather(City("Париж", 48.8534, 2.3488), 32, "Дождливая", 28),
    Weather(City("Берлин", 52.5243, 13.4105), 25, "Дождливая", 20),
    Weather(City("Рим", 41.8919, 12.5113), 27, "Жаркая", 30),
    Weather(City("Минск", 53.9000, 27.5666), 27, "Облачная", 25),
    Weather(City("Стамбул", 41.0138, 28.9496), 28, "Солнечная", 20),
    Weather(City("Вашингтон", 38.8951, -77.0363), 30, "Облачно с прояснениями", 27),
    Weather(City("Киев", 50.4546, 30.5238), 32, "Облачно с прояснениями", 32),
    Weather(City("Пекин", 39.9075, 116.3972), 32, "Облачно с прояснениями", 30)
)

fun getRussianCity() = listOf(
    Weather(City("Москва", 55.7522, 37.6155), 32, "Солнечная", 35),
    Weather(City("Санкт-Петербург", 59.9386, 30.3141), 31, "Пасмурная", 30),
    Weather(City("Новосибирск", 55.0415, 82.9346), 32, "Дождливая", 28),
    Weather(City("Екатеринбург", 56.8519, 60.6122), 25, "Дождливая", 20),
    Weather(City("Нижний Новгород", 56.3286, 44.0020), 27, "Жаркая", 30),
    Weather(City("Казань", 55.7887, 49.1221), 27, "Облачная", 25),
    Weather(City("Челябинск", 55.1540, 61.4291), 28, "Солнечная", 20),
    Weather(City("Омск",54.9924, 73.3685), 30, "Облачно с прояснениями", 27),
    Weather(City("Ростов-на-Дону", 47.2313, 39.7232), 32, "Облачно с прояснениями", 32),
    Weather(City("Уфа",54.7430, 55.9677), 32, "Облачно с прояснениями", 30)
)

