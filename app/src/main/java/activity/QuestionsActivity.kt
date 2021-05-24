package activity

import activity.databinding.QuestionBinding
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import db.database.DatabaseManager
import db.relationship.InspectionWithQuestionsAndAnswers
import shared.SharedKeys

class QuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: QuestionBinding
    private lateinit var inspectionInfo: InspectionWithQuestionsAndAnswers

    var questionNo = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.question)
        binding.prevQuestion.setOnClickListener(this)
        binding.nextQuestion.setOnClickListener(this)
        binding.mainMenu.setOnClickListener(this)
        binding.completeInspection.setOnClickListener(this)
        val dao = DatabaseManager.getInstance(applicationContext).getInspectionDAO()
        val inspectionId = intent.extras!!.getLong(SharedKeys.INSPECTION_ID)
        inspectionInfo = dao.getInspectionQuestionAnswers(inspectionId)[0]
        val inspectionLabel =
            inspectionInfo.inspection.location + " " + inspectionInfo.inspection.type
        binding.inspectionLabel.text = inspectionLabel
        setQuestionData()
    }

    private fun setQuestionData() {
        val questionInfo = inspectionInfo.questions[questionNo]
        binding.questionText.text = questionInfo.question.question


        //set answers to RecycleView
        Toast.makeText(applicationContext, "question no. $questionNo", Toast.LENGTH_SHORT).show()

        if (questionNo == 0) {
            setPrevButton(true)
        } else {
            setPrevButton(false)
        }
        if (questionNo == inspectionInfo.questions.lastIndex) {
            setNextButton(true)
        } else {
            setNextButton(false)
        }
    }


    private fun setPrevButton(disabled: Boolean){
        if (disabled){
            binding.prevQuestion.backgroundTintList = resources.getColorStateList(R.color.gray, null)
            binding.prevQuestion.isEnabled = false
        } else {
            binding.prevQuestion.backgroundTintList = resources.getColorStateList(R.color.sand, null)
            binding.prevQuestion.isEnabled = true
        }
    }

    private fun setNextButton(disabled: Boolean){
        if (disabled){
            binding.nextQuestion.backgroundTintList = resources.getColorStateList(R.color.gray, null)
            binding.nextQuestion.isEnabled = false
        } else {
            binding.nextQuestion.backgroundTintList = resources.getColorStateList(R.color.sand, null)
            binding.nextQuestion.isEnabled = true
        }
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.prev_question -> prevQuestion()
                R.id.next_question -> nextQuestion()
                R.id.complete_inspection -> completeInspection()
                R.id.main_menu -> launchMainMenu()
            }
        }
    }
    private fun prevQuestion(){
        questionNo--
        setQuestionData()
    }
    private fun nextQuestion(){
        questionNo++
        setQuestionData()
    }
    private fun completeInspection(){

    }

    private fun launchMainMenu(){
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}