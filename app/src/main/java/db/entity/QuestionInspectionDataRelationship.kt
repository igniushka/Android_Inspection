package db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["id", "questionDataId"])
data class QuestionInspectionDataRelationship(
    @ColumnInfo(name = "id") val  inspectionDataId: Long,
    @ColumnInfo(name = "questionDataId") val questionDataId: Long
    )
