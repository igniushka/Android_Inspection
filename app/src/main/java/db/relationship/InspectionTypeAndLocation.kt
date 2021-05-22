package db.relationship

import androidx.room.*


data class InspectionTypeAndLocation(
    @Embedded val user: User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "userOwnerId"
    )
    val library: Library
)
