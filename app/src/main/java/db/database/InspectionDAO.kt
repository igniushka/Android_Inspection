package db.database

import androidx.room.*
import db.entity.Answer
import db.entity.Inspection
import db.entity.Question
import db.relationship.InspectionWithQuestions
import db.relationship.InspectionWithQuestionsAndAnswers
import db.relationship.InspectionWithQuestionsAndAnswersData
import db.relationship.QuestionWithAnswers


@Dao
interface InspectionDAO {

    @Transaction
    @Query("SELECT * FROM InspectionData WHERE location = :location AND type = :type")
    fun getInspectionQuestionAnswerData(
        location: String,
        type: String
    ): List<InspectionWithQuestionsAndAnswersData>

    @Transaction
    @Query("SELECT * FROM Inspection WHERE id = :id")
    fun getInspectionQuestionAnswers(id: Long): List<InspectionWithQuestionsAndAnswers>

    @Transaction
    @Query("SELECT * FROM Inspection WHERE id = :id")
    fun getInspectionQuestions(id: Long): List<InspectionWithQuestions>


    @Transaction
    @Query("SELECT * FROM Question WHERE id = :id")
    fun getQuestionAnswers(id: Long): List<QuestionWithAnswers>

    @Insert
    fun insertNewInspection(inspection: Inspection): Long

    @Insert
    fun insertNewQuestion(question: Question): Long

    @Insert
    fun insertNewAnswer(answer: Answer)

    @Update
    fun updateQuestion(question: Question)

    @Update
    fun updateAnswer(answer: Answer)

    @Update
    fun updateInspection(inspection: Inspection)

    @Query("SELECT * FROM Inspection WHERE user = :username AND completed = :completed")
    fun getUserInspections(username: String, completed: Boolean): List<Inspection>


}