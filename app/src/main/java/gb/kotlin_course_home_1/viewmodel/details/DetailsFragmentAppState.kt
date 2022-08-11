package gb.kotlin_course_home_1.viewmodel.details

import gb.kotlin_course_home_1.domain.Weather

sealed class DetailsFragmentAppState {
    data class Success(val weatherData: Weather) : DetailsFragmentAppState()
    data class Error(val error: Throwable) : DetailsFragmentAppState()
    object Loading : DetailsFragmentAppState()
}