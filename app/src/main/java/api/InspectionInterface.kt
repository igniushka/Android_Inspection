package api

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface InspectionInterface {
    @FormUrlEncoded
    @POST("/signup")
    fun registerUser(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<ResponseBean>

    @FormUrlEncoded
    @POST("/login")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<ResponseBean>

    @FormUrlEncoded
    @POST("/verify")
    fun verify(
        @Field("token") accessToken: String,
    ): Call<ResponseBean>
}