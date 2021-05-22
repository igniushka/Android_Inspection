package db.entity

import androidx.room.*


@Entity(
    foreignKeys = [
        ForeignKey(entity = QuestionData::class,
            parentColumns = arrayOf("questionDataId"),
            childColumns = arrayOf("questionDataId"),
            onDelete = ForeignKey.CASCADE)],
    indices = [Index(
    value = ["answerName"],
    unique = true
)]
)data class AnswerData(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "answerName") val answerName: String,
    @ColumnInfo(name = "answer") val answer: String,
    @ColumnInfo(name = "questionDataId") val questionDataId: String,
)
