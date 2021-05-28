package api.bean

import com.google.gson.annotations.SerializedName

data class QuestionBean(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("question")
    var question: String? = null,
    @SerializedName("notApplicable")
    var notApplicable: Int? = null,
    @SerializedName("answers")
    var answers: List<AnswerBean>? = null
)
