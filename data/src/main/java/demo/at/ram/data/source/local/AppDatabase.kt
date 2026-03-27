package demo.at.ram.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import demo.at.ram.data.source.local.dao.CharacterDao
import demo.at.ram.data.source.local.entity.CharacterEntity
import demo.at.ram.domain.model.Crew
import demo.at.ram.domain.model.Fruit
import java.util.Date

@Database(
    entities = [CharacterEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun fromCrew(value: Crew?): String? {
        return value?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toCrew(value: String?): Crew? {
        return value?.let { gson.fromJson(it, Crew::class.java) }
    }

    @TypeConverter
    fun fromFruit(value: Fruit?): String? {
        return value?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toFruit(value: String?): Fruit? {
        return value?.let { gson.fromJson(it, Fruit::class.java) }
    }
}
