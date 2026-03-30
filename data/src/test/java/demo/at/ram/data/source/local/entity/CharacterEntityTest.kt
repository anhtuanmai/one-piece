package demo.at.ram.data.source.local.entity

import demo.at.ram.domain.model.Crew
import demo.at.ram.domain.model.Fruit
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CharacterEntityTest {

    @Test
    fun toDomainModel() {
        // Given
        val expected = CharacterEntity(
            id = 1,
            name = "Monkey D Luffy",
            status = "vivant",
            size = "174cm",
            age = "19 ans",
            bounty = "3.000.000.000",
            job = "Captain",
            crew = Crew(id = 1, name = "The Chapeau de Paille crew"),
            fruit = Fruit(id = 1, name = "Gum-Gum Fruit", filename = "https://images.api-onepiece.com/fruits/5665e89442022d4c0e7684c650dc6d6b.png")
        )

        // When
        val domainModel = expected.toDomainModel()

        // Then
        assertEquals(expected.id, domainModel.id)
        assertEquals(expected.name, domainModel.name)
        assertEquals(expected.status, domainModel.status)
        assertEquals(expected.size, domainModel.size)
        assertEquals(expected.age, domainModel.age)
        assertEquals(expected.bounty, domainModel.bounty)
        assertEquals(expected.job, domainModel.job)
        assertEquals(expected.crew, domainModel.crew)
        assertEquals(expected.fruit, domainModel.fruit)

        // When
        val reverted = CharacterEntity(domainModel)

        // Then
        assertEquals(expected, reverted)
    }

}