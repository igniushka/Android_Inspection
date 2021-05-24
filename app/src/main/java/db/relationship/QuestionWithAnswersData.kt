package db.relationship

import androidx.room.Embedded
import androidx.room.Relation
import db.entity.AnswerData
import db.entity.QuestionData

data class QuestionWithAnswersData(
    @Embedded val questionData: QuestionData,
    @Relation(
        parentColumn = "questionDataId",
        entityColumn = "questionDataId"
    )
    val answersData: List<AnswerData>
)

