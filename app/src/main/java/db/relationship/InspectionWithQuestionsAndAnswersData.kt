package db.relationship

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import db.entity.InspectionData
import db.entity.QuestionData
import db.entity.QuestionInspectionDataRelationship

data class InspectionWithQuestionsAndAnswersData(
    @Embedded val inspectionData: InspectionData,
    @Relation(
        entity = QuestionData::class,
        parentColumn = "id",
        entityColumn = "questionDataId",
        associateBy = Junction(QuestionInspectionDataRelationship::class)
    )
    val questions: List<QuestionWithAnswersData>
)

