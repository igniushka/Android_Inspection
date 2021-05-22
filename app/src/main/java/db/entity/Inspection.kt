import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import db.entity.AnswerData
import db.entity.Question

@Entity(foreignKeys = [
    ForeignKey(entity = Question::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("inspectionDataId"),
        onDelete = ForeignKey.CASCADE)])
data class Inspection(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "user") val user: String?,
    @ColumnInfo(name = "inspectionDataId") val questionId: Int,
    @ColumnInfo(name = "type") val type: String?,
    @ColumnInfo(name = "completed") val completed: Boolean?,
    )
