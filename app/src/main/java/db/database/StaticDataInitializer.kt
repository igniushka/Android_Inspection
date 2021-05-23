package db.database

import android.content.Context
import db.entity.Inspection
import db.entity.InspectionData
import db.entity.Question

const val LOCATION_A = "LOCATION A"
const val LOCATION_B = "LOCATION B"
const val LOCATION_C = "LOCATION C"

const val TYPE_1 = "TYPE 1"
const val TYPE_2 = "TYPE 2"
const val TYPE_3 = "TYPE 3"


const val QUESTION_A_1 = "Question 1 about location A"
const val QUESTION_A_2 = "Question 2 about location A"
const val QUESTION_B_1 = "Question 1 about location B"
const val QUESTION_B_2 = "Question 2 about location B"
const val QUESTION_C_1 = "Question 1 about location C"
const val QUESTION_C_2 = "Question 2 about location C"

const val QUESTION_TYPE_1_1 = "Question 1 about inspection type 1"
const val QUESTION_TYPE_1_2 = "Question 2 about inspection type 1"
const val QUESTION_TYPE_2_1 = "Question 1 about inspection type 2"
const val QUESTION_TYPE_2_2 = "Question 2 about inspection type 2"
const val QUESTION_TYPE_3_1 = "Question 1 about inspection type 3"
const val QUESTION_TYPE_3_2 = "Question 2 about inspection type 3"

const val ANSWER_1_TO_QUESTION_A_1 = "Answer 1 to Question A 1"
const val ANSWER_2_TO_QUESTION_A_1 = "Answer 2 to Question A 1"

const val ANSWER_1_TO_QUESTION_A_2 = "Answer 1 to Question A 2"
const val ANSWER_2_TO_QUESTION_A_2 = "Answer 1 to Question A 1"




class StaticDataInitializer {


    fun initialize(context: Context){
        val dao = DatabaseManager.getInstance(context).getDAO()
        val inspection1 = InspectionData(1, LOCATION_A, TYPE_1)
        val inspection2 = InspectionData(5, LOCATION_A, TYPE_2)
        val inspection3 = InspectionData(5, LOCATION_A, TYPE_3)
        val inspection4 = InspectionData(5, LOCATION_B, TYPE_1)
        val inspection5 = InspectionData(5, LOCATION_B, TYPE_2)
        val inspection6 = InspectionData(5, LOCATION_B, TYPE_3)
        val inspection7 = InspectionData(5, LOCATION_C, TYPE_1)
        val inspection8 = InspectionData(5, LOCATION_C, TYPE_2)
        val inspection9 = InspectionData(5, LOCATION_C, TYPE_3)








        dao.insertInspectionData(inspection1)
        dao.insertInspectionData(inspection2)
        dao.insertInspectionData(inspection3)
        dao.insertInspectionData(inspection4)
        dao.insertInspectionData(inspection5)
        dao.insertInspectionData(inspection6)
        dao.insertInspectionData(inspection7)
        dao.insertInspectionData(inspection8)
        dao.insertInspectionData(inspection9)

    }

}