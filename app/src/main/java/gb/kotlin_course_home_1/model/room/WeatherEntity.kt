package gb.kotlin_course_home_1.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "weather_entity_table")
data class WeatherEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val lat: Double,
    val lon: Double,
    var temperature: Int,
    var condition: String,
    var feelsLike: Int
)