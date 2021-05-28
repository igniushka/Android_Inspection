package api

import api.bean.ResponseBean
import api.bean.SubmitInspectionBean
import retrofit2.Call
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

    @POST("/submitInspection")
    fun submitInspection(
        @Body body: SubmitInspectionBean
    ): Call<ResponseBean>

    @FormUrlEncoded
    @POST("/getUserInspections")
    fun getUserInspections(
        @Field("token") token: String
    ): Call<ResponseBean>

    @FormUrlEncoded
    @POST("/getInspectionInfo")
    fun getInspectionInfo(
        @Field("token") token: String,
        @Field("inspectionId") inspectionId: Long
    ): Call<ResponseBean>


}

