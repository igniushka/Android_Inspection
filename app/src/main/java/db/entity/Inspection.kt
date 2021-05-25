package db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = InspectionData::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("inspectionDataId"),
            onDelete = ForeignKey.CASCADE
        )]
)
data class Inspection(
    @ColumnInfo(name = "user") val user: String,
    @ColumnInfo(name = "inspectionDataId") val inspectionDataId: Long,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "location") val location: String,
    @ColumnInfo(name = "completed") var completed: Boolean
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0
}

