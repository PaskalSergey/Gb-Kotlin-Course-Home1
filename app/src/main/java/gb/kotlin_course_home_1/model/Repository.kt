package gb.kotlin_course_home_1.model

import gb.kotlin_course_home_1.domain.Weather

interface Repository {
    fun getWeather(city: String): Weather
}