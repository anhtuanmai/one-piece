package demo.at.ram.domain.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Fruit(
    @SerializedName("id")
    var id: Long,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("type")
    var type: String? = null,
    @SerializedName("roman_name")
    var romanName: String? = null,
    @SerializedName("filename")
    var filename: String? = null
)
