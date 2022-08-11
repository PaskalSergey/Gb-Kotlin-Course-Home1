package gb.kotlin_course_home_1.viewmodel.details

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import gb.kotlin_course_home_1.model.*
import gb.kotlin_course_home_1.model.dto.WeatherDTO
import java.io.IOException

class DetailsViewModel(
    private val liveData: MutableLiveData<DetailsFragmentAppState> = MutableLiveData<DetailsFragmentAppState>()
) :
    ViewModel() {

    lateinit var repository: RepositoryDetails

    fun getLiveData(): MutableLiveData<DetailsFragmentAppState> {
        choiceRepository()
        return liveData
    }

    private fun choiceRepository() {
        repository = when (2) {
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

    fun getWeather(lat: Double, lon: Double) {
        choiceRepository()
        liveData.value = DetailsFragmentAppState.Loading
        repository.getWeather(lat, lon, callback)
    }

    val callback = object : MyLargeSuperCallback{
        override fun onResponse(weatherDTO: WeatherDTO) {
            liveData.postValue(DetailsFragmentAppState.Success(weatherDTO))
        }

        override fun onFailure(e: IOException) {
            liveData.postValue(DetailsFragmentAppState.Error(e))
        }
    }

    private fun isConnection(): Boolean {
        return false
    }
}