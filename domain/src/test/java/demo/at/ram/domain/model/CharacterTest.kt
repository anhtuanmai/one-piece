package demo.at.ram.domain.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CharacterTest {

    @Test
    fun testCharacterCreation() {
        val character = Character(
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

        assertEquals(1L, character.id)
        assertEquals("Monkey D Luffy", character.name)
        assertEquals("vivant", character.status)
        assertEquals("174cm", character.size)
        assertEquals("19 ans", character.age)
        assertEquals("3.000.000.000", character.bounty)
        assertEquals("Captain", character.job)
        assertEquals("The Chapeau de Paille crew", character.crew?.name)
        assertEquals("Gum-Gum Fruit", character.fruit?.name)
        assertEquals("https://images.api-onepiece.com/fruits/5665e89442022d4c0e7684c650dc6d6b.png", character.fruit?.filename)
    }
}
