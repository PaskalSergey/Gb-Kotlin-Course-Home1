package gb.kotlin_course_home_1.viewmodel.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import gb.kotlin_course_home_1.domain.City
import gb.kotlin_course_home_1.domain.Weather
import gb.kotlin_course_home_1.model.*
import gb.kotlin_course_home_1.model.dto.WeatherDTO
import gb.kotlin_course_home_1.model.retrofit.RepositoryDetailsRetrofitImpl
import java.io.IOException

class DetailsViewModel(
    private val liveData: MutableLiveData<DetailsFragmentAppState> = MutableLiveData<DetailsFragmentAppState>()
) :
    ViewModel() {

    lateinit var repository: RepositoryLocationToOneWeather

    fun getLiveData(): MutableLiveData<DetailsFragmentAppState> {
        choiceRepository()
        return liveData
    }

    private fun choiceRepository() {
        repository = when (3) {
            1 -> {
                RepositoryDetailsRetrofitImpl()
            }
            2 -> {
                RepositoryDetailsWeatherLoaderImpl()
            }
            else -> {
                RepositoryDetailsLocalImpl()
            }
        }
    }

    fun getWeather(city: City, lat: Double, lon: Double) {
        choiceRepository()
        liveData.value = DetailsFragmentAppState.Loading
        repository.getWeather(city, lat, lon, callback)
    }

    val callback = object : MyLargeSuperCallback{
        override fun onResponse(weather: Weather) {
            liveData.postValue(DetailsFragmentAppState.Success(weather))
        }

        override fun onFailure(e: IOException) {
            liveData.postValue(DetailsFragmentAppState.Error(e))
        }
    }

    private fun isConnection(): Boolean {
        return false
    }
}