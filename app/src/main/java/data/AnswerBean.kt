package data
import com.google.gson.annotations.SerializedName



class AnswerBean(){
    @SerializedName("id")
    var id: Int? = null
    @SerializedName("answer")
    var answer: String? = null
    @SerializedName("value")
    var value: Int? = null
}
