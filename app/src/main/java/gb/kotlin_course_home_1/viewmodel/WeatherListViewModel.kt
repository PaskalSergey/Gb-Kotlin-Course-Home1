package gb.kotlin_course_home_1.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import gb.kotlin_course_home_1.model.*
import kotlin.random.Random

class WeatherListViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData<AppState>()
) :
    ViewModel() {

    lateinit var repositorySets: RepositorySets
    lateinit var repositoryOne: RepositoryOne

    fun getLiveData(): MutableLiveData<AppState> {
        choiceRepository()
        return liveData
    }

    private fun choiceRepository() {
        repositoryOne = if (isConnection()) {
            RepositoryRemoteImpl()
        } else {
            RepositoryLocalImpl()
        }
        repositorySets = RepositoryLocalImpl()
    }

    fun getWeatherListForRussia(){
        sendRequest(Location.Russian)
    }

    fun getWeatherListForWorld(){
        sendRequest(Location.World)
    }

    private fun sendRequest(location: Location) {
        liveData.value = AppState.Loading
        Thread {
            Thread.sleep(100L)
            if ((0..3).random(Random(System.currentTimeMillis())) == 3) {
                liveData.postValue(AppState.Error(IllegalStateException("Ошибка")))
            } else {
                liveData.postValue(AppState.SuccessMulti(repositorySets.getListWeather(location)))
            }

        }.start()
    }

    private fun isConnection(): Boolean {
        return false
    }
}