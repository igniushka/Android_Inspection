package db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

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
    @SerializedName("questionId")
    @ColumnInfo(name = "questionId") val questionId: Long,
    @SerializedName("answerDataId")
    @ColumnInfo(name = "answerDataId") val answerDataId: Long,
    @SerializedName("answer")
    @ColumnInfo(name = "answer") val answer: String,
    @SerializedName("value")
    @ColumnInfo(name = "value") var value: Int
) {
    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
}