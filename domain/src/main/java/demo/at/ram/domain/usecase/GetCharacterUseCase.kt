package demo.at.ram.domain.usecase

import demo.at.ram.domain.model.Character
import demo.at.ram.domain.repository.OnePieceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(
    private val onePieceRepository: OnePieceRepository,
) {
    operator fun invoke(id: Long): Flow<Character> {
        val character = onePieceRepository.getCharacter(id)
        return character
    }
}