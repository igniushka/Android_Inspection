import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import db.entity.AnswerData
import db.entity.InspectionData
import db.entity.Question

@Entity(foreignKeys = [
    ForeignKey(entity = InspectionData::class,
        parentColumns = arrayOf("inspectionName"),
        childColumns = arrayOf("inspectionDataName"),
        onDelete = ForeignKey.CASCADE)])
data class Inspection(
    @PrimaryKey(autoGenerate = true)  @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "user") val user: String,
    @ColumnInfo(name = "inspectionDataName") val inspectionDataName: String,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "completed") val completed: Boolean,
    )
