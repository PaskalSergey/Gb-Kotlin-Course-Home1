package gb.kotlin_course_home_1.model

import gb.kotlin_course_home_1.domain.Weather

class RepositoryLocalImpl: Repository {
    override fun getWeather(city: String): Weather {
        return Weather()
    }
}