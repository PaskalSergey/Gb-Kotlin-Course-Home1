package gb.kotlin_course_home_1.model

import gb.kotlin_course_home_1.MyApp
import gb.kotlin_course_home_1.domain.City
import gb.kotlin_course_home_1.domain.Weather
import gb.kotlin_course_home_1.model.dto.WeatherDTO
import gb.kotlin_course_home_1.model.room.WeatherEntity

class RepositoryRoomImpl : RepositoryLocationToOneWeather, RepositoryWeatherAddable {
    override fun getWeather(city: City, lat: Double, lon: Double, callback: MyLargeSuperCallback) {
        callback.onResponse(
            MyApp.getWeatherDatabase().weatherDao().getWeatherByLocation(lat, lon).let {
                convertHistoryEntityToWeather(it).last()
            })
    }

    override fun addWeather(weather: Weather) {
        MyApp.getWeatherDatabase().weatherDao().insert(convertWeatherToEntity(weather))
    }

    private fun convertHistoryEntityToWeather(entityList: List<WeatherEntity>): List<Weather> {
        return entityList.map {
            Weather(City(it.name, it.lat, it.lon), it.temperature, it.condition, 0)
        }
    }

    private fun convertWeatherToEntity(weather: Weather): WeatherEntity {
        return WeatherEntity(
            0,
            weather.city.name,
            weather.city.lat,
            weather.city.lon,
            weather.temperature,
            weather.kindOfWeather,
            weather.feelsLike
        )
    }
}