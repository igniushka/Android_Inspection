package db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    indices = [Index(
        value = ["location", "type"],
        unique = true
    )]
)
data class InspectionData(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Long,
    @ColumnInfo(name = "period")
    val period: Int,
    @ColumnInfo(name = "location")
    val location: String,
    @ColumnInfo(name = "type")
    val type: String
)
