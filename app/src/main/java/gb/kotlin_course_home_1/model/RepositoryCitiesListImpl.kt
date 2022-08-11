package gb.kotlin_course_home_1.model

import gb.kotlin_course_home_1.domain.Weather
import gb.kotlin_course_home_1.domain.getRussianCity
import gb.kotlin_course_home_1.domain.getWorldCity

class RepositoryCitiesListImpl : RepositoryCitiesList {

    override fun getListCities(location: Location) = when (location) {
            Location.Russian -> {
                getRussianCity()
            }
            Location.World -> {
                getWorldCity()
            }
        }
    }