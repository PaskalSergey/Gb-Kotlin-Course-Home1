package gb.kotlin_course_home_1

import android.app.Application
import androidx.room.Room
import gb.kotlin_course_home_1.model.room.WeatherDatabase
import gb.kotlin_course_home_1.utils.ROOM_DB_NAME_WEATHER

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        myApp = this

    }

    companion object {
        private var myApp: MyApp? = null
        private var weatherDatabase: WeatherDatabase? = null
        fun getMyApp() = myApp!!
        fun getWeatherDatabase(): WeatherDatabase {
            if (weatherDatabase == null) {
                weatherDatabase =
                    Room.databaseBuilder(
                        getMyApp(),
                        WeatherDatabase::class.java,
                        ROOM_DB_NAME_WEATHER
                    ).allowMainThreadQueries()
                        .build()
            }
            return weatherDatabase!!
        }
    }
}