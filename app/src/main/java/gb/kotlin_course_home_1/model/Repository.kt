package gb.kotlin_course_home_1.model

import gb.kotlin_course_home_1.domain.Weather

fun interface RepositoryDetails {
    fun getWeather(lat: Double, lon: Double): Weather
}

fun interface RepositoryCitiesList {
    fun getListCities(location: Location): List<Weather>
}

sealed class Location{
    object Russian: Location()
    object World: Location()
}