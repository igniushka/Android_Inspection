package db.relationship

import androidx.room.Embedded
import androidx.room.Relation
import com.google.gson.annotations.SerializedName
import db.entity.Answer
import db.entity.Question

data class QuestionWithAnswers(
    @SerializedName("question")
    @Embedded val question: Question,
    @Relation(
        parentColumn = "id",
        entityColumn = "questionId"
    )
    @SerializedName("answer")
    val answers: List<Answer>
)
