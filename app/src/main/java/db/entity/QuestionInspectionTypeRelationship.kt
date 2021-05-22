package db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(primaryKeys = ["inspectionName", "questionName"])
data class QuestionInspectionTypeRelationship(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "inspectionName") val inspectionName: String,
    @ColumnInfo(name = "questionName") val questionName: String,
    )
