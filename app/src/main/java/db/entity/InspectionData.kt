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
data class InspectionData(
    @ColumnInfo(name = "period") val period: Int,
    @ColumnInfo(name = "location") val location: String,
    @ColumnInfo(name = "type") val type: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
}
