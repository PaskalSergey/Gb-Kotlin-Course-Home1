package gb.kotlin_course_home_1.utils

import com.google.gson.Gson
import gb.kotlin_course_home_1.model.dto.WeatherDTO
import gb.kotlin_course_home_1.utils.getLines
import gb.kotlin_course_home_1.view.details.OnResponse
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

object WeatherLoader {

    fun request(lat: Double, lon: Double, lang: String, onResponse: OnResponse) {
        val uri =
            URL("https://api.weather.yandex.ru/v2/forecast?lat=${lat}&lon=${lon}&lang=${lang}")

        var myConnection: HttpURLConnection? = null

        myConnection = uri.openConnection() as HttpURLConnection
        myConnection.readTimeout = 5000
        myConnection.addRequestProperty(
            "X-Yandex-API-Key",
            "1c3f7be9-605d-4245-9fb4-3a5338802102"
        )
        Thread {
            val reader = BufferedReader(InputStreamReader(myConnection.inputStream))
            val weatherDTO = Gson().fromJson(getLines(reader), WeatherDTO::class.java)
            onResponse.onResponse(weatherDTO)
        }.start()
    }
}