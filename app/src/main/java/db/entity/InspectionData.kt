package db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class InspectionData(
    @PrimaryKey @ColumnInfo(name = "inspectionName") val inspectionName: String,
    @ColumnInfo(name = "period") val period: Int,
)
