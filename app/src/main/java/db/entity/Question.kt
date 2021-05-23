package db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Inspection::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("inspectionId"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = QuestionData::class,
            parentColumns = arrayOf("questionDataId"),
            childColumns = arrayOf("questionDataId"),
            onDelete = ForeignKey.CASCADE
        )]
)
data class Question(
    @ColumnInfo(name = "inspectionId") val inspectionId: Int,
    @ColumnInfo(name = "questionDataId") val questionDataId: Int,
    @ColumnInfo(name = "notApplicable") val notApplicable: Boolean,
    @ColumnInfo(name = "question") val question: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0

}

