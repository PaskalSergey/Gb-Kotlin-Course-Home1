package gb.kotlin_course_home_1.model

import com.google.gson.Gson
import gb.kotlin_course_home_1.BuildConfig
import gb.kotlin_course_home_1.model.dto.WeatherDTO
import gb.kotlin_course_home_1.utils.getLines
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class RepositoryDetailsWeatherLoaderImpl : RepositoryDetails {
    override fun getWeather(lat: Double, lon: Double, callback: MyLargeSuperCallback) {
        Thread {
            val uri =
                URL("https://api.weather.yandex.ru/v2/forecast?lat=${lat}&lon=${lon}")
            var myConnection: HttpsURLConnection? = null
            myConnection = uri.openConnection() as HttpsURLConnection
            try {
                myConnection.readTimeout = 5000
                myConnection.addRequestProperty(
                    "X-Yandex-API-Key", BuildConfig.WEATHER_API_KEY
                )

                val reader = BufferedReader(InputStreamReader(myConnection.inputStream))
                val weatherDTO = Gson().fromJson(getLines(reader), WeatherDTO::class.java)
                callback.onResponse(weatherDTO)
            } catch (e: IOException) {
                callback.onFailure(e)
            } finally {
                myConnection.disconnect()
            }
        }.start()
    }
}
