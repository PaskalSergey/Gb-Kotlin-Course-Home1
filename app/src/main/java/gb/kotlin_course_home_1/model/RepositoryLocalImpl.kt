package gb.kotlin_course_home_1.model

import gb.kotlin_course_home_1.domain.Weather
import gb.kotlin_course_home_1.domain.getRussianCity
import gb.kotlin_course_home_1.domain.getWorldCity

class RepositoryLocalImpl: RepositoryOne, RepositorySets {
    override fun getWeather(city: String): Weather {
        return Weather()
    }

    override fun getListWeather(location: Location): List<Weather> {
        return when (location) {
            Location.Russian -> {
                getRussianCity()
            }
            Location.World -> {
                getWorldCity()
            }
        }
    }
}