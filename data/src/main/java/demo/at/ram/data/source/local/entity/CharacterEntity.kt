package demo.at.ram.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import demo.at.ram.domain.model.Character
import demo.at.ram.domain.model.Crew
import demo.at.ram.domain.model.Fruit

@Entity(tableName = CharacterEntity.TABLE_NAME)
class CharacterEntity(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "size") val size: String? = null,
    @ColumnInfo(name = "age") val age: String? = null,
    @ColumnInfo(name = "bounty") val bounty: String? = null,
    @ColumnInfo(name = "crew") val crew: Crew? = null,
    @ColumnInfo(name = "fruit") val fruit: Fruit? = null,
    @ColumnInfo(name = "job") val job: String? = null,
    @ColumnInfo(name = "status") val status: String? = null,
) {
    companion object {
        const val TABLE_NAME = "character_table"
    }

    constructor(character: Character) : this(
        id = character.id,
        name = character.name,
        size = character.size,
        age = character.age,
        bounty = character.bounty,
        crew = character.crew,
        fruit = character.fruit,
        job = character.job,
        status = character.status,
    )

    fun toDomainModel(): Character {
        return Character(
            id = id,
            name = name,
            size = size,
            age = age,
            bounty = bounty,
            crew = crew,
            fruit = fruit,
            job = job,
            status = status,
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CharacterEntity

        if (id != other.id) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + (name?.hashCode() ?: 0)
        return result
    }
}
