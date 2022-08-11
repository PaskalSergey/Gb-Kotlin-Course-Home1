package gb.kotlin_course_home_1.model

import gb.kotlin_course_home_1.domain.Weather
import gb.kotlin_course_home_1.model.dto.WeatherDTO
import java.io.IOException

fun interface RepositoryDetails {
    fun getWeather(lat: Double, lon: Double, callback: MyLargeSuperCallback)
}

interface MyLargeSuperCallback{
    fun onResponse(weatherDTO: WeatherDTO)
    fun onFailure(e: IOException)
}

fun interface RepositoryCitiesList {
    fun getListCities(location: Location): List<Weather>
}

sealed class Location{
    object Russian: Location()
    object World: Location()
}