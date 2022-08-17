package gb.kotlin_course_home_1.model

import gb.kotlin_course_home_1.MyApp
import gb.kotlin_course_home_1.domain.City
import gb.kotlin_course_home_1.domain.Weather
import gb.kotlin_course_home_1.model.dto.WeatherDTO
import gb.kotlin_course_home_1.model.room.WeatherEntity

class RepositoryRoomImpl : RepositoryLocationToOneWeather, RepositoryAddable {
    override fun getWeather(city: City, lat: Double, lon: Double, callback: MyLargeSuperCallback) {
        callback.onResponse(
            MyApp.getWeatherDatabase().weatherDao().getWeatherByLocation(lat, lon).let {
                convertHistoryEntityToWeather(it).last()
            })
    }

    private fun convertHistoryEntityToWeather(entityList: List<WeatherEntity>): List<Weather> {
        return entityList.map {
            Weather(City(it.name, it.lat, it.lon), it.temperature, it.condition, 0)
        }
    }

    override fun addWeather(weather: Weather) {

    }

//    fun convertWeatherToEntity(weather: Weather): WeatherEntity{
//        return WeatherEntity()
//    }

}