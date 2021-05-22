package db.relationship

import androidx.room.Embedded
import androidx.room.Relation
import db.entity.Answer
import db.entity.Question

data class QuestionWithAnswers(
    @Embedded val question: Question,
    @Relation(
        parentColumn = "id",
        entityColumn = "questionId"
    )
    val answers: List<Answer>
)
