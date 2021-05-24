package db.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import db.entity.AnswerData
import db.entity.InspectionData
import db.entity.QuestionData
import db.entity.QuestionInspectionDataRelationship

@Dao
interface InspectionDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertInspectionData(vararg inspectionData: InspectionData)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertQuestionData(vararg questionData: QuestionData)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAnswerData(vararg answerData: AnswerData)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertInspectionQuestionRelationship(vararg answerData: QuestionInspectionDataRelationship)

}