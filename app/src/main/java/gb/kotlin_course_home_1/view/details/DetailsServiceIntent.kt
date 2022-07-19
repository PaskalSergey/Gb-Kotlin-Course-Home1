package gb.kotlin_course_home_1.view.details

import android.app.IntentService
import android.content.Intent
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.gson.Gson
import gb.kotlin_course_home_1.BuildConfig
import gb.kotlin_course_home_1.domain.City
import gb.kotlin_course_home_1.model.dto.WeatherDTO
import gb.kotlin_course_home_1.utils.getLines
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.URL
import javax.net.ssl.HttpsURLConnection

const val WAVE = "WAVE"
const val BUNDLE_WEATHER_DTO_KEY = "BUNDLE_WEATHER_KEY"
const val BUNDLE_CITY_KEY = "BUNDLE_CITY_KEY"

class DetailsServiceIntent : IntentService("") {

    override fun onHandleIntent(intent: Intent?) {
        intent?.let {
            it.getParcelableExtra<City>(BUNDLE_CITY_KEY)?.let {
                val uri =
                    URL("https://api.weather.yandex.ru/v2/forecast?lat=${it.lat}&lon=${it.lon}&lang=\"ru_RU\"")

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

                        LocalBroadcastManager.getInstance(this).sendBroadcast(Intent().apply {
                            putExtra(BUNDLE_WEATHER_DTO_KEY, weatherDTO)
                            action = WAVE
                        })

                    } catch (e: Exception) {
                    } finally {
                        myConnection.disconnect()
                    }
                }.start()
            }

        }
    }
}
