package gb.kotlin_course_home_1.model

import gb.kotlin_course_home_1.domain.Weather
import gb.kotlin_course_home_1.domain.getDefaultCity
import gb.kotlin_course_home_1.domain.getRussianCity
import gb.kotlin_course_home_1.domain.getWorldCity
import gb.kotlin_course_home_1.model.dto.Fact
import gb.kotlin_course_home_1.model.dto.WeatherDTO

class RepositoryDetailsLocalImpl : RepositoryDetails {
    override fun getWeather(lat: Double, lon: Double, callback: MyLargeSuperCallback) {
        val list = getWorldCity().toMutableList()
        list.addAll(getRussianCity())
        val response = list.filter { it.city.lat == lat && it.city.lon == lon }
        callback.onResponse(convertModelToDto(response.first()))
    }

    private fun convertDtoToModel(weatherDTO: WeatherDTO): List<Weather> {
        val fact: Fact = weatherDTO.fact
        return listOf(Weather(getDefaultCity(), fact.temp, fact.condition, fact.feelsLike))
    }

    private fun convertModelToDto(weather: Weather): WeatherDTO {
        val fact: Fact = Fact(weather.kindOfWeather, weather.feelsLike, weather.temperature)
        return WeatherDTO(fact)
    }
}