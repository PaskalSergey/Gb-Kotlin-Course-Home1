package gb.kotlin_course_home_1.model

import gb.kotlin_course_home_1.domain.Weather

fun interface RepositoryOne {
    fun getWeather(city: String): Weather
}

fun interface RepositorySets {
    fun getListWeather(location: Location): List<Weather>
}

sealed class Location{
    object Russian: Location()
    object World: Location()
}