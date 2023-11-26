package ramble.sokol.inverseeducation.data.model

import com.google.gson.annotations.SerializedName

data class GetCurrentSectionsResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("category")
    val category: CategorySections?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("rate")
    val rate: String?,
    @SerializedName("comments")
    val comments: String?,
    @SerializedName("address")
    val address: String?,
    @SerializedName("author")
    val author: GetMyAccountResponse?,
)
