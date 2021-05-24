package db.database

import androidx.room.*
import db.entity.Answer
import db.entity.Inspection
import db.entity.Question
import db.relationship.InspectionWithQuestionsAndAnswers
import db.relationship.InspectionWithQuestionsAndAnswersData


@Dao
interface InspectionDAO {

    @Transaction
    @Query("SELECT * FROM InspectionData WHERE location = :location AND type = :type")
    fun getInspectionQuestionAnswerData(location: String, type: String): List<InspectionWithQuestionsAndAnswersData>

    @Transaction
    @Query("SELECT * FROM Inspection WHERE id = :id")
    fun getInspectionQuestionAnswers(id: Long): List<InspectionWithQuestionsAndAnswers>

    @Insert
    fun insertNewInspection(inspection: Inspection) : Long

    @Insert
    fun insertNewQuestion(question: Question) : Long

    @Insert
    fun insertNewAnswer(answer: Answer)

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insert(user: User?): Long


//    @Transaction
//    @Query("SELECT * FROM InspectionData WHERE location = :location AND type = :type")
//    fun getInspectionData(location: String, type: String): List<InspectionAndQuestionsData>
//
//    @Transaction
//    @Query("SELECT * FROM QuestionData WHERE questionDataId = :questionId")
//    fun getQuestionAnswerData(questionId: Int): List<QuestionWithAnswersData>



}