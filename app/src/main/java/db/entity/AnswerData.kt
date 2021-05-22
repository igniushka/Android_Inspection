package db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity
data class AnswerData(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "answer") val answer: String?,
    @ColumnInfo(name = "question") val question: String?,
)
