package ramble.sokol.inverseeducation.data.model

import com.google.gson.annotations.SerializedName

data class UserLoginEntity(
    @SerializedName("email")
    val email: String?,
    @SerializedName("password")
    val password: String?,
)
