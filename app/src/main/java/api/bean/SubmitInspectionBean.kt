package api.bean

import db.relationship.InspectionWithQuestionsAndAnswers

data class SubmitInspectionBean(
    val token: String,
    val inspectionInfo: InspectionWithQuestionsAndAnswers
)