package db.database

import android.content.Context
import db.entity.Inspection

class StaticDataInitializer {
    fun initialize(context: Context){
        val dao = DatabaseManager.getInstance(context).getDAO()
        val inspection1 = InspectionData("Inspection")
        dao.insertInspectionData()
    }

}