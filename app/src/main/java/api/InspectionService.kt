package api

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface InspectionService {
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
        @Field("token") accessToken: String
    ): Call<ResponseBean>

    @POST("/submitInspection")
    fun submitInspection(
        @Body body: SubmitInspectionBean
    ): Call<ResponseBean>

}

