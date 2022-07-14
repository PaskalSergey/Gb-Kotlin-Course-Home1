package gb.kotlin_course_home_1.view.details

import gb.kotlin_course_home_1.model.dto.WeatherDTO

fun interface OnResponse {
    fun onResponse(weather: WeatherDTO)
}