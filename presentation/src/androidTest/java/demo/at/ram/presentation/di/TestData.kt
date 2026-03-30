package demo.at.ram.presentation.di

import demo.at.ram.domain.model.Character
import demo.at.ram.domain.model.Crew
import demo.at.ram.domain.model.Fruit

object TestData {
    val fruits = listOf(
        Fruit(
            id = 1,
            name = "Gum-Gum Fruit",
            description = "Gomu Gomu no Mi, also known as the Fruit of Gum-Gum in French, is a Paramecia-type Demon Fruit that imparts the same properties as rubber to its user's body, making the eater an Elastic Man (ゴム人間, Gomu Ningen). This was once a treasure that Shanks and his crew had taken from a World Government convoy, protected by CP9, but which was accidentally eaten by Monkey D. Luffy",
            romanName = "Gomu Gomu no Mi",
            type = "Paramecia",
            filename = "https://images.api-onepiece.com/fruits/5665e89442022d4c0e7684c650dc6d6b.png"
        ),
        Fruit(
            id = 2,
            name = "Fruit of Fragmentation",
            description = "Allows you to separate your body into several parts and levitate them. The parts can fly in an area 200 meters in diameter around the feet. The feet, on the other hand, cannot levitate (although they can walk). This power makes the user immune to sharp weapons. Any part of the user will nevertheless remain sensitive to the pain felt by other detached body parts.",
            romanName = "Bara Bara no Mi",
            type = "Paramecia",
            filename = "https://images.api-onepiece.com/fruits/ce8729339b41da72e8e0f81dd7f71eee.png"
        ),
        Fruit(
            id = 3,
            name = "Fruit Glisse-Glisse",
            description = "Smoothes the user's skin, making it more beautiful. What's more, the skin becomes ultra-slick, deflecting objects touching the body.",
            romanName = "Sube Sube no Mi",
            type = "Paramecia",
            filename = "https://images.api-onepiece.com/fruits/8eb2a1bcdebacd9077ce4881a2493e2d.png"
        ),
        Fruit(
            id = 4,
            name = "Kilo-Kilo Fruit",
            description = "Allows the user's mass to be varied between a featherweight and 10 tons. Note that the user's volume does not change when he changes his mass.",
            romanName = "Kilo Kilo no Mi",
            type = "Paramecia",
            filename = "https://images.api-onepiece.com/fruits/d11cc6067afe9f178c578d0871096bbd.png"
        ),
        Fruit(
            id = 5,
            name = "Fruit Boum-Boum",
            description = "The user can make any part of his body explode, including his boogers and breath, rendering him insensitive to explosions. The user can combine his power with a pistol, which he reloads by blowing into it.",
            romanName = "Bomu Bomu no Mi",
            type = "Paramecia",
            filename = "https://images.api-onepiece.com/fruits/89ffd8470e61c97800e850ffc3bfe5c5.png"
        ),
        Fruit(
            id = 6,
            name = "Fruit Des Éclosions",
            description = "Allows the user to grow body parts (or even clones) anywhere, even on another person. He can assemble several copies of the same limb to create, for example, giant hands, or create clones of himself to deceive opponents.",
            romanName = "Hana Hana no Mi",
            type = "Paramecia",
            filename = "https://images.api-onepiece.com/fruits/5f82c2c5df83335a916f98dc3b09eade.png"
        ),
        Fruit(
            id = 7,
            name = "Ciro-Fruit",
            description = "Produces a fast-hardening wax that can be molded into any shape. This wax becomes very resistant when hardened, but remains just as vulnerable to heat as normal wax. Note that it can block most poisons.",
            romanName = "Doru Doru no Mi",
            type = "Paramecia",
            filename = "https://images.api-onepiece.com/fruits/79c5de9660c570daf6e43b8c14df7ef6.png"
        ),
        Fruit(
            id = 8,
            name = "Fruit of the Glutton",
            description = "Allows its user to eat anything and integrate this food into his own body. For example, if he eats a cannon, his tongue can transform into a real cannon. It can also fuse together the beings it has eaten, and self-ingest to slim down.",
            romanName = "Baku Baku no Mi",
            type = "Paramecia",
            filename = "https://images.api-onepiece.com/fruits/aa8f6a1e4770332fd38f90a71581ec79.png"
        ),
        Fruit(
            id = 9,
            name = "Travesti-Fruit",
            description = "Allows you to change your appearance by touching your face with your right hand, and to return to your normal appearance by touching your face with your left hand. You only need to touch a person once for their appearance to be remembered forever. The user can also assemble parts of different people, but is unable to attack unless he possesses his own face and body.",
            romanName = "Mane Mane no Mi",
            type = "Paramecia",
            filename = null
        ),
        Fruit(
            id = 10,
            name = "Sea Urchin Fruit",
            description = "Allows you to transform any part of your body (even your hair) into spikes strong enough to pierce stone walls. These spikes can also be used to perform acupuncture techniques to increase the user's own muscle mass.",
            romanName = "Toge Toge no Mi",
            type = "Paramecia",
            filename = "https://images.api-onepiece.com/fruits/dbeda9afbf6c54e261d6a61e3e081751.png"
        ),
    )

    private val strawHatCrew = Crew(
        id = 1,
        name = "The Chapeau de Paille crew",
        description = null,
        status = "assets",
        number = "10",
        romanName = "Mugiwara no Ichimi",
        totalPrime = "3.161.000.100",
        isYonko = true
    )

    val characters = listOf(
        Character(
            id = 1,
            name = "Monkey D Luffy",
            size = "174cm",
            age = "19 ans",
            bounty = "3.000.000.000",
            crew = strawHatCrew,
            fruit = Fruit(
                id = 1,
                name = "Gum-Gum Fruit",
                description = "Gomu Gomu no Mi, also known as the Fruit of Gum-Gum in French, is a Paramecia-type Demon Fruit that imparts the same properties as rubber to its user's body, making the eater an Elastic Man (ゴム人間, Gomu Ningen). This was once a treasure that Shanks and his crew had taken from a World Government convoy, protected by CP9, but which was accidentally eaten by Monkey D. Luffy",
                type = "Paramecia",
                romanName = "Gomu Gomu no Mi",
                filename = "https://images.api-onepiece.com/fruits/5665e89442022d4c0e7684c650dc6d6b.png"
            ),
            job = "Captain",
            status = "vivant"
        ),
        Character(
            id = 2,
            name = "Roronoa Zoro",
            size = "181cm",
            age = "21 ans",
            bounty = "320.000.000",
            crew = strawHatCrew,
            fruit = null,
            job = "Right-hand man",
            status = "living"
        ),
        Character(
            id = 3,
            name = "Nami",
            size = "170cm",
            age = "20 ans",
            bounty = "66.000.000",
            crew = strawHatCrew,
            fruit = null,
            job = "Navigator",
            status = "living"
        ),
        Character(
            id = 4,
            name = "Usopp",
            size = "176cm",
            age = "19 ans",
            bounty = "200.000.000",
            crew = strawHatCrew,
            fruit = null,
            job = "Sniper",
            status = "living"
        ),
        Character(
            id = 5,
            name = "Sanji",
            size = "180cm",
            age = "21 ans",
            bounty = "330.000.000",
            crew = strawHatCrew,
            fruit = null,
            job = "Cook",
            status = "living"
        ),
        Character(
            id = 6,
            name = "Tony-Tony Chopper",
            size = "90cm",
            age = "17 ans",
            bounty = "100",
            crew = strawHatCrew,
            fruit = Fruit(
                id = 92,
                name = "Fruit of the Human",
                description = "whoever eats it becomes half-human. As the user was originally a reindeer, he acquired an intelligence that enabled him to acquire a vast amount of knowledge. Before the ellipse, Chopper had to take a Rumble-Ball to get more transformations for 3 minutes1 (if after 3 minutes he took another Rumble-Ball, he no longer controlled his transformations, and if he took a 3rd Rumble-Ball, he became a gigantic but unconscious monster, destroying enemies and allies alike). After the ellipse, he masters all transformations perfectly, and when he takes a Rumble-Ball he becomes the gigantic monster, but in control of himself.",
                type = "Zoan",
                romanName = "Hito Hito no Mi",
                filename = "https://images.api-onepiece.com/fruits/28c479a3d76524452745f40bde7a0c37.png"
            ),
            job = "Doctor",
            status = "living"
        ),
        Character(
            id = 7,
            name = "Nico Robin",
            size = "188cm",
            age = "30 ans",
            bounty = "130.000.000",
            crew = strawHatCrew,
            fruit = Fruit(
                id = 6,
                name = "Fruit Des Éclosions",
                description = "Allows the user to grow body parts (or even clones) anywhere, even on another person. He can assemble several copies of the same limb to create, for example, giant hands, or create clones of himself to deceive opponents.",
                type = "Paramecia",
                romanName = "Hana Hana no Mi",
                filename = "https://images.api-onepiece.com/fruits/5f82c2c5df83335a916f98dc3b09eade.png"
            ),
            job = "Archaeologist",
            status = "living"
        ),
        Character(
            id = 8,
            name = "Franky",
            size = "240cm",
            age = "36 ans",
            bounty = "94.000.000",
            crew = strawHatCrew,
            fruit = null,
            job = "Carpenter",
            status = "living"
        ),
        Character(
            id = 9,
            name = "Brook",
            size = "277cm",
            age = "90 ans",
            bounty = "83.000.000",
            crew = strawHatCrew,
            fruit = Fruit(
                id = 21,
                name = "Fruit of the Resurrection",
                description = "Allows the owner to come back to life after death (this power only works once). Its possessor can take his soul out of his body, take it through walls and return it to his body as he wishes. Similarly, if his head is cut off, his fruit enables him to bring it back to his shoulders by sheer force of will (the soul is still connected to his body). If his power is combined with a sword, he can freeze any enemy he touches, thanks to the icy cold of the depths that his soul brings to his sword.",
                type = "Paramecia",
                romanName = "Yomi Yomi no Mi",
                filename = "https://images.api-onepiece.com/fruits/56fc9cdf59bcd8a6e53b3e7c31e11005.png"
            ),
            job = "Musician",
            status = "living"
        ),
        Character(
            id = 10,
            name = "Jinbe",
            size = "301cm",
            age = "46 ans",
            bounty = "438.000.000",
            crew = strawHatCrew,
            fruit = null,
            job = "Helmsman",
            status = "living"
        ),
    )
}

