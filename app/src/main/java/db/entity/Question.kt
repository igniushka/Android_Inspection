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
    @ColumnInfo(name = "inspectionId") val inspectionId: Long,
    @ColumnInfo(name = "questionDataId") val questionDataId: Long,
    @ColumnInfo(name = "question") val question: String,
    @ColumnInfo(name = "notApplicable") var notApplicable: Boolean,
    ) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0
}
