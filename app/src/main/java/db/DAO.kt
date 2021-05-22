package db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import db.entity.InspectionType

@Dao
interface DAO {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertInspectionType(vararg inspectionType: InspectionType)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertQuestion(vararg inspectionType: InspectionType)

}