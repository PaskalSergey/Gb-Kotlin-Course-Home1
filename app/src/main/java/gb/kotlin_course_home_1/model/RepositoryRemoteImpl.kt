package gb.kotlin_course_home_1.model

import gb.kotlin_course_home_1.domain.Weather
import gb.kotlin_course_home_1.domain.getRussianCity
import gb.kotlin_course_home_1.domain.getWorldCity
import gb.kotlin_course_home_1.viewmodel.AppState

class RepositoryRemoteImpl : RepositoryOne {
    override fun getWeather(city: String) = Weather()
}