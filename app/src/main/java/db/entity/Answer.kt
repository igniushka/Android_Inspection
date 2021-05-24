package db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Question::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("questionId"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = AnswerData::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("answerDataId"),
            onDelete = ForeignKey.CASCADE
        )]
)
data class Answer(
    @ColumnInfo(name = "questionId") val questionId: Long,
    @ColumnInfo(name = "answerDataId") val answerDataId: Long,
    @ColumnInfo(name = "answerName") val answerName: String,
    @ColumnInfo(name = "answer") val answer: String,
    @ColumnInfo(name = "value") val value: Int
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
}