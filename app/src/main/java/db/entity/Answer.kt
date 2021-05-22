package db.entity

import Inspection
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [
    ForeignKey(entity = Question::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("questionId"),
    onDelete = ForeignKey.CASCADE),
    ForeignKey(entity = AnswerData::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("answerDataId"),
        onDelete = ForeignKey.CASCADE)])
data class Answer(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "questionId") val questionId: Int,
    @ColumnInfo(name = "answerDataId") val answerDataId: Int,
    @ColumnInfo(name = "value") val value: Int,
)