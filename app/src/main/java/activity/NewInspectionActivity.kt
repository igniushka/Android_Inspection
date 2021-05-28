package activity

import activity.databinding.NewInspectionBinding
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import db.database.*
import db.entity.Answer
import db.entity.Inspection
import db.entity.Question
import db.relationship.InspectionWithQuestionsAndAnswers
import shared.SharedKeys
import shared.SharedKeys.INSPECTION_ID
import shared.SharedPreferenceWriter


class NewInspectionActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: NewInspectionBinding
    private var prefs: SharedPreferenceWriter? = null
    private val locations = arrayOf(LOCATION_A, LOCATION_B, LOCATION_C)
    private val types = arrayOf(TYPE_1, TYPE_2, TYPE_3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefs = SharedPreferenceWriter.getInstance(applicationContext)
        binding = DataBindingUtil.setContentView(this, R.layout.new_inspection)
        binding.newInspection.setOnClickListener(this)
        binding.back.setOnClickListener(this)
        val locationAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, locations)
        val typeAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, types)
        binding.locationDropdown.adapter = locationAdapter
        binding.typeDropdown.adapter = typeAdapter
    }

    private fun createNewInspection(){
        binding.progressBarCyclic.visibility = View.VISIBLE
        val type = binding.typeDropdown.selectedItem.toString()
        val location = binding.locationDropdown.selectedItem.toString()
        val dao = DatabaseManager.getInstance(applicationContext).getInspectionDAO()
        val inspectionQuestionsAnswers  = dao.getInspectionQuestionAnswerData(location, type)[0]
        val inspectionData = inspectionQuestionsAnswers.inspectionData
        val username = prefs?.getString(SharedKeys.USERNAME).toString()
        val newInspection = Inspection(username, inspectionData.id, inspectionData.type, inspectionData.location, false)
        val newInspectionId = dao.insertNewInspection(newInspection)
        for (questionWithAnswers in inspectionQuestionsAnswers.questions){
            val questionData = questionWithAnswers.questionData
            val newQuestion = Question(newInspectionId, questionData.questionDataId, questionData.question, false)
            val questionId = dao.insertNewQuestion(newQuestion)
            for (answerData in questionWithAnswers.answersData){
                val newAnswer = Answer(questionId, answerData.id, answerData.answer, 0)
                dao.insertNewAnswer(newAnswer)
            }
        }
        binding.progressBarCyclic.visibility = View.INVISIBLE
        startActivity(Intent(this, QuestionsActivity::class.java).putExtra(INSPECTION_ID, newInspectionId))
        finish()
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.new_inspection -> createNewInspection()
                R.id.back -> onBackPressed()
            }
        }

    }
}