package demo.at.ram.domain.usecase

import demo.at.ram.domain.model.Character
import demo.at.ram.domain.repository.OnePieceRepository
import demo.at.ram.shared.model.ResponseResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCharactersUseCase @Inject constructor(
    private val onePieceRepository: OnePieceRepository
) {
    operator fun invoke(): Flow<ResponseResult<List<Character>>> {
        return onePieceRepository.getAllCharacters()
    }
}