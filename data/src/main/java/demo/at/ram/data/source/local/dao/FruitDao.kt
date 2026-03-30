package demo.at.ram.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import demo.at.ram.data.source.local.entity.FruitEntity

@Dao
interface FruitDao {

    @Query("SELECT * FROM ${FruitEntity.TABLE_NAME} ORDER BY id ASC")
    suspend fun getAll(): List<FruitEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(fruits: List<FruitEntity>) : List<Long>
}
