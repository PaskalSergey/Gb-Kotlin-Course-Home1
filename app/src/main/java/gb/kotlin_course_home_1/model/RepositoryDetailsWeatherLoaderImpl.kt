package gb.kotlin_course_home_1.model

import gb.kotlin_course_home_1.domain.Weather
import gb.kotlin_course_home_1.domain.getDefaultCity

class RepositoryDetailsWeatherLoaderImpl : RepositoryDetails {
    override fun getWeather(lat: Double, lon: Double): Weather {
        return Weather(getDefaultCity())
    }
}
