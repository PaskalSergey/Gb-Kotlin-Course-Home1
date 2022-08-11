package gb.kotlin_course_home_1.viewmodel.details

import gb.kotlin_course_home_1.model.dto.WeatherDTO

sealed class DetailsFragmentAppState {
    data class Success(val weatherData: WeatherDTO) : DetailsFragmentAppState()
    data class Error(val error: Throwable) : DetailsFragmentAppState()
    object Loading : DetailsFragmentAppState()
}