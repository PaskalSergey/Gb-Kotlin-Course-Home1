package gb.kotlin_course_home_1.model

import gb.kotlin_course_home_1.domain.City
import gb.kotlin_course_home_1.domain.Weather
import gb.kotlin_course_home_1.model.dto.WeatherDTO
import java.io.IOException

fun interface RepositoryLocationToOneWeather {
    fun getWeather(city: City, lat: Double, lon: Double, callback: MyLargeSuperCallback)
}

fun interface RepositoryAddable{
    fun addWeather(weather: Weather)

}

interface MyLargeSuperCallback{
    fun onResponse(weather: Weather)
    fun onFailure(e: IOException)
}

fun interface RepositoryCitiesList {
    fun getListCities(location: Location): List<Weather>
}

sealed class Location{
    object Russian: Location()
    object World: Location()
}