package ramble.sokol.inverseeducation.data.model

import com.google.gson.annotations.SerializedName

data class GetMyAccountResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("firstname")
    val firstname: String?,
    @SerializedName("lastname")
    val lastname: String?,
    @SerializedName("surname")
    val surname: String?,
    @SerializedName("school")
    val school: Int?,
    @SerializedName("school_class")
    val school_class: Int?,
    @SerializedName("role")
    val role: Int?,
    @SerializedName("achievements")
    val achievements: List<String>?,
)
