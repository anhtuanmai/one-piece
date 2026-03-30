package demo.at.ram.domain.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Character(
    @SerializedName("id")
    var id: Long,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("size")
    var size: String? = null,
    @SerializedName("age")
    var age: String? = null,
    @SerializedName("bounty")
    var bounty: String? = null,
    @SerializedName("crew")
    var crew: Crew? = null,
    @SerializedName("fruit")
    var fruit: Fruit? = null,
    @SerializedName("job")
    var job: String? = null,
    @SerializedName("status")
    var status: String? = null
)
