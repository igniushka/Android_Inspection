package db.database

import android.content.Context
import db.entity.AnswerData
import db.entity.InspectionData
import db.entity.QuestionData
import db.entity.QuestionInspectionDataRelationship

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
const val ANSWER_2_TO_QUESTION_A_2 = "Answer 2 to Question A 2"
const val ANSWER_1_TO_QUESTION_B_1 = "Answer 1 to Question B 1"
const val ANSWER_2_TO_QUESTION_B_1 = "Answer 2 to Question B 1"
const val ANSWER_1_TO_QUESTION_B_2 = "Answer 1 to Question B 2"
const val ANSWER_2_TO_QUESTION_B_2 = "Answer 2 to Question B 2"
const val ANSWER_1_TO_QUESTION_C_1 = "Answer 1 to Question C 1"
const val ANSWER_2_TO_QUESTION_C_1 = "Answer 2 to Question C 1"
const val ANSWER_1_TO_QUESTION_C_2 = "Answer 1 to Question C 2"
const val ANSWER_2_TO_QUESTION_C_2 = "Answer 2 to Question C 2"

const val ANSWER_1_TO_QUESTION_1_TYPE_1 = "Answer 1 to Question 1 about Type 1"
const val ANSWER_2_TO_QUESTION_1_TYPE_1 = "Answer 2 to Question 1 about Type 1"
const val ANSWER_1_TO_QUESTION_2_TYPE_1 = "Answer 1 to Question 2 about Type 1"
const val ANSWER_2_TO_QUESTION_2_TYPE_1 = "Answer 2 to Question 2 about Type 1"
const val ANSWER_1_TO_QUESTION_1_TYPE_2 = "Answer 1 to Question 1 about Type 2"
const val ANSWER_2_TO_QUESTION_1_TYPE_2 = "Answer 2 to Question 1 about Type 2"
const val ANSWER_1_TO_QUESTION_2_TYPE_2 = "Answer 1 to Question 2 about Type 2"
const val ANSWER_2_TO_QUESTION_2_TYPE_2 = "Answer 2 to Question 2 about Type 2"
const val ANSWER_1_TO_QUESTION_1_TYPE_3 = "Answer 1 to Question 1 about Type 3"
const val ANSWER_2_TO_QUESTION_1_TYPE_3 = "Answer 2 to Question 1 about Type 3"
const val ANSWER_1_TO_QUESTION_2_TYPE_3 = "Answer 1 to Question 2 about Type 3"
const val ANSWER_2_TO_QUESTION_2_TYPE_3 = "Answer 2 to Question 2 about Type 3"


class StaticDataInitializer {

    fun initialize(context: Context){
        val dao = DatabaseManager.getInstance(context).getStaticDataDAO()

        //Create InspectionData objects
        val inspection1 = InspectionData(1, 1, LOCATION_A, TYPE_1)
        val inspection2 = InspectionData(2, 5, LOCATION_A, TYPE_2)
        val inspection3 = InspectionData(3, 5, LOCATION_A, TYPE_3)
        val inspection4 = InspectionData(4, 5, LOCATION_B, TYPE_1)
        val inspection5 = InspectionData(5, 5, LOCATION_B, TYPE_2)
        val inspection6 = InspectionData(6, 5, LOCATION_B, TYPE_3)
        val inspection7 = InspectionData(7, 5, LOCATION_C, TYPE_1)
        val inspection8 = InspectionData(8, 5, LOCATION_C, TYPE_2)
        val inspection9 = InspectionData(9, 5, LOCATION_C, TYPE_3)

        //Create QuestionData objects
        val question1 = QuestionData(1, QUESTION_A_1, QUESTION_A_1)
        val question2 = QuestionData(2, QUESTION_A_2, QUESTION_A_2)
        val question3 = QuestionData(3, QUESTION_B_1, QUESTION_B_1)
        val question4 = QuestionData(4, QUESTION_B_2, QUESTION_B_2)
        val question5 = QuestionData(5, QUESTION_C_1, QUESTION_C_1)
        val question6 = QuestionData(6, QUESTION_C_2, QUESTION_C_2)
        val question7 = QuestionData(7, QUESTION_TYPE_1_1, QUESTION_TYPE_1_1)
        val question8 = QuestionData(8, QUESTION_TYPE_1_2, QUESTION_TYPE_1_2)
        val question9 = QuestionData(9, QUESTION_TYPE_2_1, QUESTION_TYPE_2_1)
        val question10 = QuestionData(10, QUESTION_TYPE_2_2, QUESTION_TYPE_2_2)
        val question11 = QuestionData(11, QUESTION_TYPE_3_1, QUESTION_TYPE_3_1)
        val question12 = QuestionData(12, QUESTION_TYPE_3_2, QUESTION_TYPE_3_2)

        //Create AnswerData objects
        val answer1 = AnswerData(1, ANSWER_1_TO_QUESTION_A_1, ANSWER_1_TO_QUESTION_A_1, 1)
        val answer2 = AnswerData(2, ANSWER_2_TO_QUESTION_A_1, ANSWER_2_TO_QUESTION_A_1, 1)
        val answer3 = AnswerData(3, ANSWER_1_TO_QUESTION_A_2, ANSWER_1_TO_QUESTION_A_2, 2)
        val answer4 = AnswerData(4, ANSWER_2_TO_QUESTION_A_2, ANSWER_2_TO_QUESTION_A_2, 2)
        val answer5 = AnswerData(5, ANSWER_1_TO_QUESTION_B_1, ANSWER_1_TO_QUESTION_B_1, 3)
        val answer6 = AnswerData(6, ANSWER_2_TO_QUESTION_B_1, ANSWER_2_TO_QUESTION_B_1, 3)
        val answer7 = AnswerData(7, ANSWER_1_TO_QUESTION_B_2, ANSWER_1_TO_QUESTION_B_2, 4)
        val answer8 = AnswerData(8, ANSWER_2_TO_QUESTION_B_2, ANSWER_2_TO_QUESTION_B_2, 4)
        val answer9 = AnswerData(9, ANSWER_1_TO_QUESTION_C_1, ANSWER_1_TO_QUESTION_C_1, 5)
        val answer10 = AnswerData(10, ANSWER_2_TO_QUESTION_C_1, ANSWER_2_TO_QUESTION_C_1, 5)
        val answer11 = AnswerData(11, ANSWER_1_TO_QUESTION_C_2, ANSWER_1_TO_QUESTION_C_2, 6)
        val answer12 = AnswerData(12, ANSWER_2_TO_QUESTION_C_2, ANSWER_2_TO_QUESTION_C_2, 6)
        val answer13 = AnswerData(13, ANSWER_1_TO_QUESTION_1_TYPE_1, ANSWER_1_TO_QUESTION_1_TYPE_1, 7)
        val answer14 = AnswerData(14, ANSWER_2_TO_QUESTION_1_TYPE_1, ANSWER_2_TO_QUESTION_1_TYPE_1, 7)
        val answer15 = AnswerData(15, ANSWER_1_TO_QUESTION_2_TYPE_1, ANSWER_1_TO_QUESTION_2_TYPE_1, 8)
        val answer16 = AnswerData(16, ANSWER_2_TO_QUESTION_2_TYPE_1, ANSWER_2_TO_QUESTION_2_TYPE_1, 8)
        val answer17 = AnswerData(17, ANSWER_1_TO_QUESTION_1_TYPE_2, ANSWER_1_TO_QUESTION_1_TYPE_2, 9)
        val answer18 = AnswerData(18, ANSWER_2_TO_QUESTION_1_TYPE_2, ANSWER_2_TO_QUESTION_1_TYPE_2, 9)
        val answer19 = AnswerData(19, ANSWER_1_TO_QUESTION_2_TYPE_2, ANSWER_1_TO_QUESTION_2_TYPE_2, 10)
        val answer20 = AnswerData(20, ANSWER_2_TO_QUESTION_2_TYPE_2, ANSWER_2_TO_QUESTION_2_TYPE_2, 10)
        val answer21 = AnswerData(21, ANSWER_1_TO_QUESTION_1_TYPE_3, ANSWER_1_TO_QUESTION_1_TYPE_3, 11)
        val answer22 = AnswerData(22, ANSWER_2_TO_QUESTION_1_TYPE_3, ANSWER_2_TO_QUESTION_1_TYPE_3, 11)
        val answer23 = AnswerData(23, ANSWER_1_TO_QUESTION_2_TYPE_3, ANSWER_1_TO_QUESTION_2_TYPE_3, 12)
        val answer24 = AnswerData(24, ANSWER_2_TO_QUESTION_2_TYPE_3, ANSWER_2_TO_QUESTION_2_TYPE_3, 12)

        //Create Inspection-Question Relationship objects
        val inspectionQuestion1 = QuestionInspectionDataRelationship(1, 1)
        val inspectionQuestion2 = QuestionInspectionDataRelationship(1, 2)
        val inspectionQuestion3 = QuestionInspectionDataRelationship(1, 7)
        val inspectionQuestion4 = QuestionInspectionDataRelationship(1, 8)
        val inspectionQuestion5 = QuestionInspectionDataRelationship(2, 1)
        val inspectionQuestion6 = QuestionInspectionDataRelationship(2, 2)
        val inspectionQuestion7 = QuestionInspectionDataRelationship(2, 9)
        val inspectionQuestion8 = QuestionInspectionDataRelationship(2, 10)
        val inspectionQuestion9 = QuestionInspectionDataRelationship(3, 1)
        val inspectionQuestion10 = QuestionInspectionDataRelationship(3, 2)
        val inspectionQuestion11 = QuestionInspectionDataRelationship(3, 11)
        val inspectionQuestion12 = QuestionInspectionDataRelationship(3, 12)
        val inspectionQuestion13 = QuestionInspectionDataRelationship(4, 3)
        val inspectionQuestion14 = QuestionInspectionDataRelationship(4, 4)
        val inspectionQuestion15 = QuestionInspectionDataRelationship(4, 7)
        val inspectionQuestion16 = QuestionInspectionDataRelationship(4, 8)
        val inspectionQuestion17 = QuestionInspectionDataRelationship(5, 3)
        val inspectionQuestion18 = QuestionInspectionDataRelationship(5, 4)
        val inspectionQuestion19 = QuestionInspectionDataRelationship(5, 9)
        val inspectionQuestion20 = QuestionInspectionDataRelationship(5, 10)
        val inspectionQuestion21 = QuestionInspectionDataRelationship(6, 3)
        val inspectionQuestion22 = QuestionInspectionDataRelationship(6, 4)
        val inspectionQuestion23 = QuestionInspectionDataRelationship(6, 11)
        val inspectionQuestion24 = QuestionInspectionDataRelationship(6, 12)
        val inspectionQuestion25 = QuestionInspectionDataRelationship(7, 5)
        val inspectionQuestion26 = QuestionInspectionDataRelationship(7, 6)
        val inspectionQuestion27 = QuestionInspectionDataRelationship(7, 7)
        val inspectionQuestion28 = QuestionInspectionDataRelationship(7, 8)
        val inspectionQuestion29 = QuestionInspectionDataRelationship(8, 5)
        val inspectionQuestion30 = QuestionInspectionDataRelationship(8, 6)
        val inspectionQuestion31 = QuestionInspectionDataRelationship(8, 9)
        val inspectionQuestion32 = QuestionInspectionDataRelationship(8, 10)
        val inspectionQuestion33 = QuestionInspectionDataRelationship(9, 5)
        val inspectionQuestion34 = QuestionInspectionDataRelationship(9, 6)
        val inspectionQuestion35 = QuestionInspectionDataRelationship(9, 11)
        val inspectionQuestion36 = QuestionInspectionDataRelationship(9, 12)

        //Insert all DataObjects into the database. The insertions will fail if the primary key already exist, avoiding storing duplicate data.
        dao.insertInspectionData(inspection1)
        dao.insertInspectionData(inspection2)
        dao.insertInspectionData(inspection3)
        dao.insertInspectionData(inspection4)
        dao.insertInspectionData(inspection5)
        dao.insertInspectionData(inspection6)
        dao.insertInspectionData(inspection7)
        dao.insertInspectionData(inspection8)
        dao.insertInspectionData(inspection9)

        dao.insertQuestionData(question1)
        dao.insertQuestionData(question2)
        dao.insertQuestionData(question3)
        dao.insertQuestionData(question4)
        dao.insertQuestionData(question5)
        dao.insertQuestionData(question6)
        dao.insertQuestionData(question7)
        dao.insertQuestionData(question8)
        dao.insertQuestionData(question9)
        dao.insertQuestionData(question10)
        dao.insertQuestionData(question11)
        dao.insertQuestionData(question12)

        dao.insertAnswerData(answer1)
        dao.insertAnswerData(answer2)
        dao.insertAnswerData(answer3)
        dao.insertAnswerData(answer4)
        dao.insertAnswerData(answer5)
        dao.insertAnswerData(answer6)
        dao.insertAnswerData(answer7)
        dao.insertAnswerData(answer8)
        dao.insertAnswerData(answer9)
        dao.insertAnswerData(answer10)
        dao.insertAnswerData(answer11)
        dao.insertAnswerData(answer12)
        dao.insertAnswerData(answer13)
        dao.insertAnswerData(answer14)
        dao.insertAnswerData(answer15)
        dao.insertAnswerData(answer16)
        dao.insertAnswerData(answer17)
        dao.insertAnswerData(answer18)
        dao.insertAnswerData(answer19)
        dao.insertAnswerData(answer20)
        dao.insertAnswerData(answer21)
        dao.insertAnswerData(answer22)
        dao.insertAnswerData(answer23)
        dao.insertAnswerData(answer24)

        dao.insertInspectionQuestionRelationship(inspectionQuestion1)
        dao.insertInspectionQuestionRelationship(inspectionQuestion2)
        dao.insertInspectionQuestionRelationship(inspectionQuestion3)
        dao.insertInspectionQuestionRelationship(inspectionQuestion4)
        dao.insertInspectionQuestionRelationship(inspectionQuestion5)
        dao.insertInspectionQuestionRelationship(inspectionQuestion6)
        dao.insertInspectionQuestionRelationship(inspectionQuestion7)
        dao.insertInspectionQuestionRelationship(inspectionQuestion8)
        dao.insertInspectionQuestionRelationship(inspectionQuestion9)
        dao.insertInspectionQuestionRelationship(inspectionQuestion10)
        dao.insertInspectionQuestionRelationship(inspectionQuestion11)
        dao.insertInspectionQuestionRelationship(inspectionQuestion12)
        dao.insertInspectionQuestionRelationship(inspectionQuestion13)
        dao.insertInspectionQuestionRelationship(inspectionQuestion14)
        dao.insertInspectionQuestionRelationship(inspectionQuestion15)
        dao.insertInspectionQuestionRelationship(inspectionQuestion16)
        dao.insertInspectionQuestionRelationship(inspectionQuestion17)
        dao.insertInspectionQuestionRelationship(inspectionQuestion18)
        dao.insertInspectionQuestionRelationship(inspectionQuestion19)
        dao.insertInspectionQuestionRelationship(inspectionQuestion20)
        dao.insertInspectionQuestionRelationship(inspectionQuestion21)
        dao.insertInspectionQuestionRelationship(inspectionQuestion22)
        dao.insertInspectionQuestionRelationship(inspectionQuestion23)
        dao.insertInspectionQuestionRelationship(inspectionQuestion24)
        dao.insertInspectionQuestionRelationship(inspectionQuestion25)
        dao.insertInspectionQuestionRelationship(inspectionQuestion26)
        dao.insertInspectionQuestionRelationship(inspectionQuestion27)
        dao.insertInspectionQuestionRelationship(inspectionQuestion28)
        dao.insertInspectionQuestionRelationship(inspectionQuestion29)
        dao.insertInspectionQuestionRelationship(inspectionQuestion30)
        dao.insertInspectionQuestionRelationship(inspectionQuestion31)
        dao.insertInspectionQuestionRelationship(inspectionQuestion32)
        dao.insertInspectionQuestionRelationship(inspectionQuestion33)
        dao.insertInspectionQuestionRelationship(inspectionQuestion34)
    }
}