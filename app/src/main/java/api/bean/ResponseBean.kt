package api.bean

import com.google.gson.annotations.SerializedName

class ResponseBean {
    @SerializedName("message")
    var message: String? = null

    @SerializedName("token")
    var token: String? = null

    @SerializedName("inspections")
    var inspections: List<SubmittedInspectionBean>? = null

    @SerializedName("inspection")
    var inspection: SubmittedInspectionBean? = null


}