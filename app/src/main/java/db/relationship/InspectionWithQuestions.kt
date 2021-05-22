package db.relationship

import Inspection
import androidx.room.Embedded
import androidx.room.Relation
import db.entity.Question

data class InspectionWithQuestions(
    @Embedded val inspection: Inspection,
    @Relation(
        parentColumn = "id",
        entityColumn = "inspectionId"
    )
    val questions: List<Question>
)

