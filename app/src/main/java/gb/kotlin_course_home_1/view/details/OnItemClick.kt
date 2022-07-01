package gb.kotlin_course_home_1.view.details

import gb.kotlin_course_home_1.domain.Weather

fun interface OnItemClick {
    fun onItemClick(weather: Weather)
}