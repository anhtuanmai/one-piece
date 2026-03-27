package demo.at.ram.presentation.di

import demo.at.ram.domain.model.Character
import demo.at.ram.domain.model.Crew
import demo.at.ram.domain.model.Fruit

object TestData {
    val characters = listOf(
        Character(
            id = 1,
            name = "Monkey D Luffy",
            size = "174cm",
            age = "19 ans",
            bounty = "3.000.000.000",
            job = "Captain",
            status = "vivant",
            crew = Crew(id = 1, name = "The Chapeau de Paille crew"),
            fruit = Fruit(id = 1, name = "Gum-Gum Fruit", filename = "https://images.api-onepiece.com/fruits/5665e89442022d4c0e7684c650dc6d6b.png")
        ),
        Character(
            id = 7,
            name = "Roronoa Zoro",
            size = "181cm",
            age = "21 ans",
            bounty = "1.111.000.000",
            job = "Swordsman",
            status = "vivant",
            crew = Crew(id = 1, name = "The Chapeau de Paille crew"),
            fruit = null
        )
    )
}

}