package db.entity

import Inspection
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(foreignKeys = [
    ForeignKey(entity = Inspection::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("inspectionId"),
        onDelete = ForeignKey.CASCADE),
    ForeignKey(entity = QuestionData::class,
        parentColumns = arrayOf("questionDataId"),
        childColumns = arrayOf("questionDataId"),
        onDelete = ForeignKey.CASCADE)])
data class Question(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "inspectionId") val inspectionId: Int,
    @ColumnInfo(name = "questionDataId") val questionDataId: Int,
    @ColumnInfo(name = "notApplicable") val notApplicable: Boolean,
    @ColumnInfo(name = "question") val question: String,

    )

