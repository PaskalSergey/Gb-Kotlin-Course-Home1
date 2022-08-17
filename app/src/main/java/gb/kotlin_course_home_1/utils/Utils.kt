package gb.kotlin_course_home_1.utils

import gb.kotlin_course_home_1.domain.City
import gb.kotlin_course_home_1.domain.Weather
import gb.kotlin_course_home_1.model.dto.Fact
import gb.kotlin_course_home_1.model.dto.WeatherDTO
import java.io.BufferedReader
import java.util.stream.Collectors

class Utils {
}

fun getLines(reader: BufferedReader): String {
    return reader.lines().collect(Collectors.joining("\n"))
}

fun convertDtoToModel(weatherDTO: WeatherDTO, city: City): Weather {
    val fact: Fact = weatherDTO.fact
    return (Weather(city, fact.temp, fact.condition, fact.feelsLike))
}

fun convertModelToDto(weather: Weather): WeatherDTO {
    val fact: Fact = Fact(weather.kindOfWeather, weather.feelsLike, weather.temperature)
    return WeatherDTO(fact)
}