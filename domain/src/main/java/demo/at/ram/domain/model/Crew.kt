package demo.at.ram.domain.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Crew(
    @SerializedName("id")
    var id: Long,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("status")
    var status: String? = null,
    @SerializedName("number")
    var number: String? = null,
    @SerializedName("roman_name")
    var romanName: String? = null,
    @SerializedName("total_prime")
    var totalPrime: String? = null,
    @SerializedName("is_yonko")
    var isYonko: Boolean? = null
)
