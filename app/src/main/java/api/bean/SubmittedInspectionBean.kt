package api.bean

import com.google.gson.annotations.SerializedName

data class SubmittedInspectionBean(
    @SerializedName("id")
    val id: Long,
    @SerializedName("user")
    val user: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("date")
    val date: String
)