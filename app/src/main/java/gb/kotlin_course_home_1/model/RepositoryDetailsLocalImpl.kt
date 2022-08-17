package gb.kotlin_course_home_1.model

import gb.kotlin_course_home_1.domain.City
import gb.kotlin_course_home_1.domain.getRussianCity
import gb.kotlin_course_home_1.domain.getWorldCity

class RepositoryDetailsLocalImpl : RepositoryLocationToOneWeather {
    override fun getWeather(city: City, lat: Double, lon: Double, callback: MyLargeSuperCallback) {
        val list = getWorldCity().toMutableList()
        list.addAll(getRussianCity())
        val response = list.filter { it.city.lat == lat && it.city.lon == lon }
        callback.onResponse(response.first())
    }
}