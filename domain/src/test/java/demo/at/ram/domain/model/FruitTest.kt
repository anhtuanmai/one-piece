package demo.at.ram.domain.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class FruitTest {

    @Test
    fun testFruitCreation() {
        val fruit = Fruit(
            id = 1,
            name = "Gum-Gum Fruit",
            description = "Allows the user's body to stretch like rubber",
            type = "Paramecia",
            romanName = "Gomu Gomu no Mi",
            filename = "https://images.api-onepiece.com/fruits/gomu.png"
        )

        assertEquals(1L, fruit.id)
        assertEquals("Gum-Gum Fruit", fruit.name)
        assertEquals("Allows the user's body to stretch like rubber", fruit.description)
        assertEquals("Paramecia", fruit.type)
        assertEquals("Gomu Gomu no Mi", fruit.romanName)
        assertEquals("https://images.api-onepiece.com/fruits/gomu.png", fruit.filename)
    }

    @Test
    fun testFruitDefaultValues() {
        val fruit = Fruit(id = 2)

        assertEquals(2L, fruit.id)
        assertNull(fruit.name)
        assertNull(fruit.description)
        assertNull(fruit.type)
        assertNull(fruit.romanName)
        assertNull(fruit.filename)
    }
}
