package demo.at.ram.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import demo.at.ram.domain.model.Fruit

@Entity(tableName = FruitEntity.TABLE_NAME)
class FruitEntity(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "name") val name: String? = null,
    @ColumnInfo(name = "description") val description: String? = null,
    @ColumnInfo(name = "type") val type: String? = null,
    @ColumnInfo(name = "roman_name") val romanName: String? = null,
    @ColumnInfo(name = "filename") val filename: String? = null,
) {
    companion object {
        const val TABLE_NAME = "fruit_table"
    }

    constructor(fruit: Fruit) : this(
        id = fruit.id,
        name = fruit.name,
        description = fruit.description,
        type = fruit.type,
        romanName = fruit.romanName,
        filename = fruit.filename,
    )

    fun toDomainModel(): Fruit {
        return Fruit(
            id = id,
            name = name,
            description = description,
            type = type,
            romanName = romanName,
            filename = filename,
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FruitEntity

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
