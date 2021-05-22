package db.entity

import Inspection
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity( foreignKeys = [
    ForeignKey(entity = Inspection::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("inspectionId"),
        onDelete = ForeignKey.CASCADE),
    ForeignKey(entity = QuestionData::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("answerTypeId"),
        onDelete = ForeignKey.CASCADE)])
data class Question(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "inspectionId") val inspectionId: Int,
    @ColumnInfo(name = "answerTypeId") val answerTypeId: Int,
    @ColumnInfo(name = "value") val value: Int,
)

