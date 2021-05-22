package db.relationship

import Inspection
import androidx.room.*
import db.entity.InspectionData


data class InspectionAndType(
    @Embedded val type: InspectionData,
    @Relation(
        parentColumn = "name",
        entityColumn = "type"
    )
    val inspection: Inspection
)
