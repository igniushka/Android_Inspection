package db.relationship

import androidx.room.Embedded
import androidx.room.Relation
import db.entity.Inspection
import db.entity.Question

data class InspectionWithQuestions(
    @Embedded val inspection: Inspection,
    @Relation(
        parentColumn = "id",
        entityColumn = "inspectionId"
    )
    val questions: List<Question>
){

}

