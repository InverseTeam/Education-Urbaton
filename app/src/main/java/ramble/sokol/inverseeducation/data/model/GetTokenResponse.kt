package ramble.sokol.inverseeducation.data.model

import com.google.gson.annotations.SerializedName

data class GetTokenResponse(
    @SerializedName("auth_token")
    val token: String?,
)
