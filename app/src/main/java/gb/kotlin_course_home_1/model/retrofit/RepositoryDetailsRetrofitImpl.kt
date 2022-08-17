package gb.kotlin_course_home_1.model.retrofit

import com.google.gson.GsonBuilder
import gb.kotlin_course_home_1.BuildConfig
import gb.kotlin_course_home_1.domain.City
import gb.kotlin_course_home_1.model.MyLargeSuperCallback
import gb.kotlin_course_home_1.model.RepositoryLocationToOneWeather
import gb.kotlin_course_home_1.model.dto.WeatherDTO
import gb.kotlin_course_home_1.utils.convertDtoToModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


class RepositoryDetailsRetrofitImpl : RepositoryLocationToOneWeather {
    override fun getWeather(city: City, lat: Double, lon: Double, callback: MyLargeSuperCallback) {
        val retrofitImpl = Retrofit.Builder()
        retrofitImpl.baseUrl("https://api.weather.yandex.ru")
        retrofitImpl.addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
            )
        )
        val api = retrofitImpl.build().create(WeatherAPI::class.java)
        api.getWeather(BuildConfig.WEATHER_API_KEY, lat, lon)
            .enqueue(object : Callback<WeatherDTO> {
                override fun onResponse(call: Call<WeatherDTO>, response: Response<WeatherDTO>) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onResponse(convertDtoToModel(response.body()!!, city))
                    } else {
                        callback.onFailure(IOException())
                    }
                }

                override fun onFailure(call: Call<WeatherDTO>, t: Throwable) {
                    callback.onFailure(t as IOException)
                }
            })
    }
}