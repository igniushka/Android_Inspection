package db.relationship

import androidx.room.Embedded
import androidx.room.Relation
import com.google.gson.annotations.SerializedName
import db.entity.Inspection
import db.entity.Question


data class InspectionWithQuestionsAndAnswers(
    @SerializedName("inspection")
    @Embedded val inspection: Inspection,
    @Relation(
        entity = Question::class,
        parentColumn = "id",
        entityColumn = "inspectionId"
    )
    @SerializedName("questions")
    val questions: List<QuestionWithAnswers>
)
