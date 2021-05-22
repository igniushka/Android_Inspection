package api

import com.google.gson.annotations.SerializedName

class ResponseBean {
    @SerializedName("message")
    var message: String? = null
    @SerializedName("token")
    var token: String? = null
}