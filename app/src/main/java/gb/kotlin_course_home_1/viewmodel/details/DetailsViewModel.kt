package gb.kotlin_course_home_1.viewmodel.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import gb.kotlin_course_home_1.model.*
import kotlin.random.Random

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
        repository = when (1) {
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
        //   liveData.postValue(DetailsFragmentAppState.Error(IllegalStateException("Ошибка")))
        liveData.postValue(DetailsFragmentAppState.Success(repository.getWeather(lat, lon)))
    }

    private fun isConnection(): Boolean {
        return false
    }
}