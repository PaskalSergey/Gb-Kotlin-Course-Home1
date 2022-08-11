package gb.kotlin_course_home_1.viewmodel.citieslist

import gb.kotlin_course_home_1.domain.Weather

sealed class CityListFragmentAppState {
    data class SuccessOne(val weatherData: Weather) : CityListFragmentAppState()
    data class SuccessMulti(val weatherData: List<Weather>) : CityListFragmentAppState()
    data class Error(val error: Throwable) : CityListFragmentAppState()
    object Loading : CityListFragmentAppState()
}