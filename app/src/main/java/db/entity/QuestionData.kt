package db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    indices = [Index(
        value = ["questionName"],
        unique = true
    )]
)
data class QuestionData(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "questionDataId")
    val questionDataId: Long,
    @ColumnInfo(name = "questionName") val questionName: String,
    @ColumnInfo(name = "question") val question: String
)