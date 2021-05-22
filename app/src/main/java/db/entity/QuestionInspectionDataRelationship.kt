package db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["inspectionName", "questionDataId"])
data class QuestionInspectionDataRelationship(
    @ColumnInfo(name = "inspectionName") val inspectionName: String,
    @ColumnInfo(name = "questionDataId") val questionDataId: String,
    )
