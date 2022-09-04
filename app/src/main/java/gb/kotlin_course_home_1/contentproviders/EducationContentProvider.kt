package gb.kotlin_course_home_1.contentproviders

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import gb.kotlin_course_home_1.MyApp.Companion.getWeatherDatabase
import gb.kotlin_course_home_1.R
import gb.kotlin_course_home_1.model.room.WeatherEntity
import gb.kotlin_course_home_1.utils.ID
import gb.kotlin_course_home_1.utils.NAME
import gb.kotlin_course_home_1.utils.TEMPERATURE

private const val URI_ALL = 1
private const val URI_ID = 2
private const val ENTITY_PATH = "weather_entity_table"

class EducationContentProvider : ContentProvider() {

    private var authorities: String? = null
    private lateinit var uriMatcher: UriMatcher

    private var entityContentType: String? = null
    private var entityContentItemType: String? = null

    private lateinit var contentUri: Uri

    override fun onCreate(): Boolean {
        context?.let {
            authorities = it.resources.getString(R.string.authorities)
            uriMatcher = UriMatcher(UriMatcher.NO_MATCH)
            uriMatcher.addURI(authorities, ENTITY_PATH, URI_ALL)
            uriMatcher.addURI(authorities, "$ENTITY_PATH/#", URI_ID)
            entityContentType = "vnd.android.cursor.dir/vnd.$authorities.$ENTITY_PATH"
            entityContentItemType = "vnd.android.cursor.item/vnd.$authorities.$ENTITY_PATH"
            contentUri = Uri.parse("content://${authorities}/${ENTITY_PATH}")
        }
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        val weatherDAO = getWeatherDatabase().weatherDao()

        val responseCursor = when (uriMatcher.match(uri)) {
            URI_ALL -> {
                weatherDAO.getWeatherCursor()
            }
            URI_ID -> {
                val id = ContentUris.parseId(uri)
                weatherDAO.getWeatherCursor(id)
            }
            else -> {
                throw IllegalStateException("Bad query")
            }
        }
        responseCursor.setNotificationUri(context!!.contentResolver, contentUri)
        return responseCursor
    }

    override fun getType(uri: Uri): String? {
        return when (uriMatcher.match(uri)) {
            URI_ALL -> {
                entityContentType
            }
            URI_ID -> {
                entityContentItemType
            }
            else -> {
                throw IllegalStateException("Bad query")
            }
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val weatherDAO = getWeatherDatabase().weatherDao()
        val weather = mapperRawToWeatherEntity(values)
        weatherDAO.insert(weather)
        context!!.contentResolver.notifyChange(contentUri, null)
        return ContentUris.withAppendedId(contentUri, weather.id)
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        val weatherDAO = getWeatherDatabase().weatherDao()
        val id = ContentUris.parseId(uri)
        weatherDAO.deleteById(id)
        context!!.contentResolver.notifyChange(contentUri, null)
        return 1
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        val weatherDAO = getWeatherDatabase().weatherDao()
        val id = ContentUris.parseId(uri)
        weatherDAO.update(mapperRawToWeatherEntity(values))
        context!!.contentResolver.notifyChange(contentUri, null)
        return 1
    }

    private fun mapperRawToWeatherEntity(values: ContentValues?): WeatherEntity {
        return if (values == null) {
            WeatherEntity()
        } else {
            val id = if (values.containsKey(ID)) values[ID] as Long else 0
            val name = values[NAME] as String
            val temperature = values[TEMPERATURE] as Int
            WeatherEntity(id, name, temperature = temperature)
        }
    }


}