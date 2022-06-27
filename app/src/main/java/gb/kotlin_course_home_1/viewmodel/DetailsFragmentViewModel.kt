package gb.kotlin_course_home_1.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import gb.kotlin_course_home_1.model.Repository
import gb.kotlin_course_home_1.model.RepositoryLocalImpl
import gb.kotlin_course_home_1.model.RepositoryRemoteImpl
import kotlin.random.Random

class DetailsFragmentViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData<AppState>()
) :
    ViewModel() {

    lateinit var repository: Repository

    fun getLiveData(): MutableLiveData<AppState> {
        choiceRepository()
        return liveData
    }

    private fun choiceRepository() {
        repository = if (isConnection()) {
            RepositoryRemoteImpl()
        } else {
            RepositoryLocalImpl()
        }
    }

    fun sendRequest() {
        liveData.value = AppState.Loading
        if (Random.nextInt(1, 2) == 2) {
            liveData.postValue(AppState.Error(throw IllegalStateException("Ошибка")))
        } else {
            liveData.postValue(AppState.Success(repository.getWeather("Донецк")))
        }

    }

    private fun isConnection(): Boolean {
        return false
    }
}