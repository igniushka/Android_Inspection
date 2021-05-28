package db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

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
    @SerializedName("user")
    @ColumnInfo(name = "user") val user: String,
    @SerializedName("inspectionDataId")
    @ColumnInfo(name = "inspectionDataId") var inspectionDataId: Long =-1,
    @SerializedName("type")
    @ColumnInfo(name = "type") val type: String,
    @SerializedName("location")
    @ColumnInfo(name = "location") val location: String,
    @SerializedName("completed")
    @ColumnInfo(name = "completed") var completed: Boolean
) {
    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0
}

