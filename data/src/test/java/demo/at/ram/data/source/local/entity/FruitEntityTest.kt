package demo.at.ram.data.source.local.entity

import demo.at.ram.domain.model.Fruit
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FruitEntityTest {

    @Test
    fun toDomainModel() {
        // Given
        val entity = FruitEntity(
            id = 1,
            name = "Gum-Gum Fruit",
            description = "Allows the user's body to stretch like rubber",
            type = "Paramecia",
            romanName = "Gomu Gomu no Mi",
            filename = "https://images.api-onepiece.com/fruits/gomu.png"
        )

        // When
        val domainModel = entity.toDomainModel()

        // Then
        assertEquals(entity.id, domainModel.id)
        assertEquals(entity.name, domainModel.name)
        assertEquals(entity.description, domainModel.description)
        assertEquals(entity.type, domainModel.type)
        assertEquals(entity.romanName, domainModel.romanName)
        assertEquals(entity.filename, domainModel.filename)

        // When - round trip
        val reverted = FruitEntity(domainModel)

        // Then
        assertEquals(entity, reverted)
    }
}
