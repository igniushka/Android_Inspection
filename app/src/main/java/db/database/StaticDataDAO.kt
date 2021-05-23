package db.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import db.entity.AnswerData
import db.entity.InspectionData
import db.entity.QuestionData
import db.entity.QuestionInspectionDataRelationship

@Dao
interface StaticDataDAO {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertInspectionData(vararg inspectionData: InspectionData)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertQuestionData(vararg questionData: QuestionData)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertAnswerData(vararg answerData: AnswerData)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertInspectionQuestionRelationship(vararg answerData: QuestionInspectionDataRelationship)

}