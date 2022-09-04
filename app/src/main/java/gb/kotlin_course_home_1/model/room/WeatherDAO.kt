package gb.kotlin_course_home_1.model.room

import android.database.Cursor
import androidx.room.*

@Dao
interface WeatherDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(weatherEntity: WeatherEntity)

    @Update
    fun update(weatherEntity: WeatherEntity)

    @Query("SELECT * FROM weather_entity_table WHERE lat=:mLat AND lon=:mLon")
    fun getWeatherByLocation(mLat: Double, mLon: Double): List<WeatherEntity>

    @Query("SELECT * FROM weather_entity_table")
    fun getWeatherAll(): List<WeatherEntity>

    @Query("DELETE FROM weather_entity_table WHERE id = :id")
    fun deleteById(id: Long)

    @Query("SELECT id, name, temperature FROM weather_entity_table")
    fun getWeatherCursor(): Cursor

    @Query("SELECT id, name, temperature FROM weather_entity_table WHERE id = :id")
    fun getWeatherCursor(id: Long): Cursor

}