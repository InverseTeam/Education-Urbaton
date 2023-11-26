package ramble.sokol.inverseeducation.data.util

import io.reactivex.Single
import ramble.sokol.inverseeducation.data.model.GetAllSectionsResponse
import ramble.sokol.inverseeducation.data.model.GetCurrentSectionsResponse
import ramble.sokol.inverseeducation.data.model.GetMyAccountResponse
import ramble.sokol.inverseeducation.data.model.GetNewsResponse
import ramble.sokol.inverseeducation.data.model.GetTokenResponse
import ramble.sokol.inverseeducation.data.model.UserLoginEntity
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiMethod {

    @POST("users/auth/token/login/")
    fun getToken(
        @Body body: UserLoginEntity
    ): Call<GetTokenResponse>

    @GET("users/auth/users/me/")
    fun getMyAccount(
        @Header("Authorization") token: String
    ): Call<GetMyAccountResponse>

    @GET("news/")
    fun getNews(
        @Header("Authorization") token: String
    ): Call<List<GetNewsResponse>>

    @GET("sections/")
    fun getAllSections(
        @Header("Authorization") token: String
    ): Call<List<GetAllSectionsResponse>>

    @GET("sections/{id}/")
    fun getCurrentSections(
        @Path("id") id: Int,
        @Header("Authorization") token: String
    ): Call<GetCurrentSectionsResponse>

}