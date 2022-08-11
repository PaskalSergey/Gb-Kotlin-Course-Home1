package gb.kotlin_course_home_1.utils

import com.google.gson.Gson
import gb.kotlin_course_home_1.BuildConfig
import gb.kotlin_course_home_1.model.dto.WeatherDTO
import gb.kotlin_course_home_1.utils.getLines
import gb.kotlin_course_home_1.view.details.OnResponse
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection

object WeatherLoader {

    fun request(lat: Double, lon: Double, lang: String, onResponse: OnResponse) {
        val uri =
            URL("https://api.weather.yandex.ru/v2/forecast?lat=${lat}&lon=${lon}&lang=${lang}")

        Thread {
            var myConnection: HttpsURLConnection? = null
            myConnection = uri.openConnection() as HttpsURLConnection
            try {
                myConnection.readTimeout = 5000
                myConnection.addRequestProperty(
                    "X-Yandex-API-Key", BuildConfig.WEATHER_API_KEY
                )

                val reader = BufferedReader(InputStreamReader(myConnection.inputStream))
                val weatherDTO = Gson().fromJson(getLines(reader), WeatherDTO::class.java)
                onResponse.onResponse(weatherDTO)
            } catch (e: Exception) {
            } finally {
                myConnection.disconnect()
            }
        }.start()

    }
}