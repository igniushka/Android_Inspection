package db.relationship

import androidx.room.Embedded
import androidx.room.Relation
import db.entity.Inspection
import db.entity.Question


data class InspectionWithQuestionsAndAnswers(
    @Embedded val inspection: Inspection,
    @Relation(
        entity = Question::class,
        parentColumn = "id",
        entityColumn = "inspectionId"
    )
    val questions: List<QuestionWithAnswers>
)
