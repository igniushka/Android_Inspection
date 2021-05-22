package db.relationship

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import db.entity.InspectionData
import db.entity.QuestionData
import db.entity.QuestionInspectionDataRelationship

data class InspectionAndQuestionsData(
    @Embedded val inspectionData: InspectionData,
    @Relation(
        parentColumn = "inspectionDataId",
        entityColumn = "questionDataId",
        associateBy = Junction(QuestionInspectionDataRelationship::class)
    )
    val questionsData: List<QuestionData>
)
