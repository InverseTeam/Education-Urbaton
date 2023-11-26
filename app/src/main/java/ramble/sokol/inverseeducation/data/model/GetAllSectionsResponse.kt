package ramble.sokol.inverseeducation.data.model

import com.google.gson.annotations.SerializedName

data class GetAllSectionsResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("category")
    val category: CategorySections?,
)
