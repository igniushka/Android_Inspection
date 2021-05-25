package activity

import activity.databinding.QuestionBinding
import adapter.AnswerAdapter
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import db.database.DatabaseManager
import db.database.InspectionDAO
import db.relationship.InspectionWithQuestions
import db.relationship.QuestionWithAnswers
import shared.SharedKeys


class QuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: QuestionBinding
    private lateinit var inspectionInfo: InspectionWithQuestions
    private lateinit var dao: InspectionDAO
    private var questionInfo: QuestionWithAnswers? = null

    private var questionNo = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dao = DatabaseManager.getInstance(applicationContext).getInspectionDAO()
        binding = DataBindingUtil.setContentView(this, R.layout.question)
        binding.prevQuestion.setOnClickListener(this)
        binding.nextQuestion.setOnClickListener(this)
        binding.mainMenu.setOnClickListener(this)
        binding.completeInspection.setOnClickListener(this)
        binding.naCheckbox.setOnCheckedChangeListener(object :
            CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                val question = questionInfo?.question
                if (question != null) {
                    question.notApplicable = isChecked
                    dao.updateQuestion(question)
                }
            }
        })
        val inspectionId = intent.extras!!.getLong(SharedKeys.INSPECTION_ID)
        inspectionInfo = dao.getInspectionQuestions(inspectionId)[0]
        val inspectionLabel =
            inspectionInfo.inspection.location + " " + inspectionInfo.inspection.type
        binding.inspectionLabel.text = inspectionLabel
        setQuestionData()
    }

    private fun setQuestionData() {
        val questionId = inspectionInfo.questions[questionNo].id
        questionInfo = dao.getQuestionAnswers(questionId)[0]
        binding.questionText.text = questionInfo?.question?.question
        binding.naCheckbox.isChecked = questionInfo?.question?.notApplicable!!
        setAnswers()

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

    private fun setAnswers() {
        val answersAdapter = questionInfo?.let { AnswerAdapter(it.answers, dao) }
        val linearLayoutManager = LinearLayoutManager(
            applicationContext
        )
        binding.answerRecycler.layoutManager = linearLayoutManager
        binding.answerRecycler.adapter = answersAdapter
    }


    private fun setPrevButton(disabled: Boolean) {
        if (disabled) {
            binding.prevQuestion.backgroundTintList =
                resources.getColorStateList(R.color.gray, null)
            binding.prevQuestion.isEnabled = false
        } else {
            binding.prevQuestion.backgroundTintList =
                resources.getColorStateList(R.color.sand, null)
            binding.prevQuestion.isEnabled = true
        }
    }

    private fun setNextButton(disabled: Boolean) {
        if (disabled) {
            binding.nextQuestion.backgroundTintList =
                resources.getColorStateList(R.color.gray, null)
            binding.nextQuestion.isEnabled = false
        } else {
            binding.nextQuestion.backgroundTintList =
                resources.getColorStateList(R.color.sand, null)
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

    private fun prevQuestion() {
        questionNo--
        setQuestionData()
    }

    private fun nextQuestion() {
        questionNo++
        setQuestionData()
    }

    private fun completeInspection() {
        AlertDialog.Builder(this)
            .setTitle("Please Confirm")
            .setMessage("Are you sure you have completed this inspection?")
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setPositiveButton(
                "Yes"
            ) { _, _ ->
                val inspection = inspectionInfo.inspection
                inspection.completed = true
                dao.updateInspection(inspection)
                Toast.makeText(
                    this,
                    "Inspection completed",
                    Toast.LENGTH_SHORT
                ).show()
                launchMainMenu()

            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }.show()

    }

    private fun launchMainMenu() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}