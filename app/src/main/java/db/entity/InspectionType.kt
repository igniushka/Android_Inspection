package db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class InspectionType(
    @PrimaryKey @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "period") val period: Int,
)
