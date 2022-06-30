package gb.kotlin_course_home_1.model

import gb.kotlin_course_home_1.domain.Weather
import gb.kotlin_course_home_1.viewmodel.AppState

class RepositoryRemoteImpl: Repository {
    override fun getWeather(city: String): Weather {
        Thread {
            Thread.sleep(200L)

        }.start()
        return Weather()
    }
}