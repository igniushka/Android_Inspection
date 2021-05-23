package db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["inspectionDataId", "questionDataId"])
data class QuestionInspectionDataRelationship(
    @ColumnInfo(name = "inspectionDataId") val  inspectionDataId: Int,
    @ColumnInfo(name = "questionDataId") val questionDataId: Int
    )
