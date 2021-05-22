package db.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import db.entity.AnswerData
import db.entity.InspectionData
import db.entity.QuestionData

@Dao
interface DAO {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertInspectionData(vararg inspectionData: InspectionData)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertQuestionData(vararg questionData: QuestionData)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertAnswerData(vararg answerData: AnswerData)

}