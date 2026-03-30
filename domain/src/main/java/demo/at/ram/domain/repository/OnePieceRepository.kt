package demo.at.ram.domain.repository

import demo.at.ram.domain.model.Character
import demo.at.ram.domain.model.Fruit
import demo.at.ram.shared.model.ResponseResult
import kotlinx.coroutines.flow.Flow

interface OnePieceRepository {

    /**
     * Get all characters from remote
     */
    fun getAllCharacters(): Flow<ResponseResult<List<Character>>>

    fun getAllFruits(): Flow<ResponseResult<List<Fruit>>>

    /**
     * Get all characters from local
     */
    fun getSavedCharacters(): Flow<List<Character>>

    fun getCharacter(id: Long): Flow<Character>
}