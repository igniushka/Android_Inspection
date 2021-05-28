package api.bean

import com.google.gson.annotations.SerializedName

data class AnswerBean(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("answer")
    var answer: String? = null,
    @SerializedName("value")
    var value: Int? = null
)