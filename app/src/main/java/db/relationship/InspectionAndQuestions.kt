package db.relationship

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import db.entity.InspectionData
import db.entity.QuestionData
import db.entity.QuestionInspectionTypeRelationship

data class InspectionAndQuestions(
    @Embedded val inspectionType: InspectionData,
    @Relation(
        parentColumn = "inspectionName",
        entityColumn = "questionName",
        associateBy = Junction(QuestionInspectionTypeRelationship::class)
    )
    val questions: List<QuestionData>

)
