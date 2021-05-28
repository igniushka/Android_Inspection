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
data class InspectionReminder(
    @ColumnInfo(name = "user")
    val user: String,
    @ColumnInfo(name = "period")
    val period: Int,
    @ColumnInfo(name = "location")
    val location: String,
    @ColumnInfo(name = "type")
    val type: String,
    @ColumnInfo(name = "date")
    val date: Long
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
}
