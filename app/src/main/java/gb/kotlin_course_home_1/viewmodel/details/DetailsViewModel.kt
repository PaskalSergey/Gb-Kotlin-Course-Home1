package gb.kotlin_course_home_1.viewmodel.details

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import gb.kotlin_course_home_1.MyApp
import gb.kotlin_course_home_1.domain.City
import gb.kotlin_course_home_1.domain.Weather
import gb.kotlin_course_home_1.model.*
import gb.kotlin_course_home_1.model.retrofit.RepositoryDetailsRetrofitImpl
import java.io.IOException

class DetailsViewModel(
    private val liveData: MutableLiveData<DetailsFragmentAppState> = MutableLiveData<DetailsFragmentAppState>()
) :
    ViewModel() {

    lateinit var repositoryLocationToOneWeather: RepositoryLocationToOneWeather
    lateinit var repositoryWeatherAddable: RepositoryWeatherAddable

    fun getLiveData(): MutableLiveData<DetailsFragmentAppState> {
        choiceRepository()
        return liveData
    }

    private fun choiceRepository() {
        if (isConnection()) {
            repositoryLocationToOneWeather = when (3) {
                1 -> {
                    RepositoryDetailsRetrofitImpl()
                }
                2 -> {
                    RepositoryDetailsWeatherLoaderImpl()
                }
                3 -> {
                    RepositoryRoomImpl()
                }
                else -> {
                    RepositoryDetailsLocalImpl()
                }
            }
            repositoryWeatherAddable = when (0) {
                1 -> {
                    RepositoryRoomImpl()
                }
                else -> {
                    RepositoryRoomImpl()
                }
            }
        } else {
            repositoryLocationToOneWeather = when (1) {
                1 -> {
                    RepositoryRoomImpl()
                }
                2 -> {
                    RepositoryDetailsLocalImpl()
                }
                else -> {
                    RepositoryDetailsLocalImpl()
                }
            }
            repositoryWeatherAddable = when (0) {
                1 -> {
                    RepositoryRoomImpl()
                }
                else -> {
                    RepositoryRoomImpl()
                }
            }
        }


    }

    fun getWeather(city: City, lat: Double, lon: Double) {
        liveData.value = DetailsFragmentAppState.Loading
        repositoryLocationToOneWeather.getWeather(city, lat, lon, callback)
    }

    val callback = object : MyLargeSuperCallback {
        override fun onResponse(weather: Weather) {
            if(isConnection()){
                repositoryWeatherAddable.addWeather(weather)
            }

            liveData.postValue(DetailsFragmentAppState.Success(weather))
        }

        override fun onFailure(e: IOException) {
            liveData.postValue(DetailsFragmentAppState.Error(e))
        }
    }

    private fun isConnection(): Boolean {
        return true
    }
}