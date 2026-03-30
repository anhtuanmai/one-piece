package demo.at.ram.data.source.local

import demo.at.ram.data.source.local.dao.CharacterDao
import demo.at.ram.data.source.local.dao.FruitDao
import demo.at.ram.data.source.local.entity.CharacterEntity
import demo.at.ram.data.source.local.entity.FruitEntity
import javax.inject.Inject

/**
 * Local character data source
 */
class LocalDataSource @Inject constructor(
    private val characterDao: CharacterDao,
    private val fruitDao: FruitDao,
) {

    suspend fun loadCharacters(): List<CharacterEntity> {
        return characterDao.getAll()
    }

    suspend fun saveCharacters(characters: List<CharacterEntity>): List<Long> {
        return characterDao.insertAll(characters)
    }

    suspend fun loadFruits(): List<FruitEntity> {
        return fruitDao.getAll()
    }

    suspend fun saveFruits(fruits: List<FruitEntity>): List<Long> {
        return fruitDao.insertAll(fruits)
    }

}