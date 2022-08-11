package gb.kotlin_course_home_1.viewmodel.citieslist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import gb.kotlin_course_home_1.model.*
import kotlin.random.Random

class CitiesListViewModel(
    private val liveData: MutableLiveData<CityListFragmentAppState> = MutableLiveData<CityListFragmentAppState>()
) :
    ViewModel() {

    lateinit var repositoryCitiesList: RepositoryCitiesList

    fun getLiveData(): MutableLiveData<CityListFragmentAppState> {
        choiceRepository()
        return liveData
    }

    private fun choiceRepository() {
        repositoryCitiesList = RepositoryCitiesListImpl()
    }

    fun getWeatherListForRussia(){
        sendRequest(Location.Russian)
    }

    fun getWeatherListForWorld(){
        sendRequest(Location.World)
    }

    private fun sendRequest(location: Location) {
        liveData.value = CityListFragmentAppState.Loading
        Thread {
            Thread.sleep(100L)
            if ((0..3).random(Random(System.currentTimeMillis())) == 3) {
                liveData.postValue(CityListFragmentAppState.Error(IllegalStateException("Ошибка")))
            } else {
                liveData.postValue(CityListFragmentAppState.SuccessMulti(repositoryCitiesList.getListCities(location)))
            }

        }.start()
    }

    private fun isConnection(): Boolean {
        return false
    }
}