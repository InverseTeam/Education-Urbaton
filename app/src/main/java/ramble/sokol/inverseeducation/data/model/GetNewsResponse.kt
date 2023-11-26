package ramble.sokol.inverseeducation.data.model

import com.google.gson.annotations.SerializedName

data class GetNewsResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("cover")
    val cover: String?,
    @SerializedName("author")
    val author: GetMyAccountResponse?,
    @SerializedName("content")
    val content: String?,
    @SerializedName("timedate")
    val timedate: String?,
)
