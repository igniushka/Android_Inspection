package activity

import activity.databinding.QuestionBinding
import adapter.AnswerAdapter
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import api.InspectionViewModel
import db.database.DatabaseManager
import db.database.InspectionDAO
import db.entity.InspectionReminder
import db.relationship.InspectionWithQuestions
import db.relationship.QuestionWithAnswers
import shared.SharedKeys
import shared.SharedPreferenceWriter
import util.NetworkUtils


class QuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: QuestionBinding
    private var inspectionId: Long = -1
    private lateinit var inspectionInfo: InspectionWithQuestions
    private lateinit var dao: InspectionDAO
    private var questionInfo: QuestionWithAnswers? = null
    private var completed = false
    private var prefs: SharedPreferenceWriter? = null


    private var questionNo = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        completed = intent.extras?.getBoolean(SharedKeys.COMPLETED) == true
        dao = DatabaseManager.getInstance(applicationContext).getInspectionDAO()
        binding = DataBindingUtil.setContentView(this, R.layout.question)
        prefs = SharedPreferenceWriter.getInstance(applicationContext)
        binding.prevQuestion.setOnClickListener(this)
        binding.nextQuestion.setOnClickListener(this)
        binding.back.setOnClickListener(this)
        binding.naCheckbox.isEnabled = !completed
        binding.naCheckbox.setOnCheckedChangeListener { _, isChecked ->
            val question = questionInfo?.question
            if (question != null) {
                question.notApplicable = isChecked
                dao.updateQuestion(question)
            }
        }
        inspectionId = intent.extras!!.getLong(SharedKeys.INSPECTION_ID)
        inspectionInfo = dao.getInspectionQuestions(inspectionId)[0]
        val inspectionLabel =
            inspectionInfo.inspection.location + " " + inspectionInfo.inspection.type
        binding.inspectionLabel.text = inspectionLabel
        setQuestionData()
        if (completed) {
            binding.completeInspection.visibility = View.GONE
            binding.submitInspection.setOnClickListener(this)

        } else {
            binding.submitInspection.visibility = View.GONE
            binding.completeInspection.setOnClickListener(this)

        }

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
        val answersAdapter = questionInfo?.let { AnswerAdapter(it.answers, dao, completed) }
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
                R.id.back -> back()
                R.id.submit_inspection -> submitInspection()
            }
        }
    }

    override fun onBackPressed() {
        Context.CONNECTIVITY_SERVICE
        back()
    }

    private fun back() {
        startActivity(
            Intent(
                this,
                InspectionListActivity::class.java
            ).putExtra(SharedKeys.COMPLETED, completed.toString())
        )
        finish()
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
                back()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }.show()
    }

    private fun submitInspection() {
        if (NetworkUtils.isConnected(applicationContext)) {
            val inspection = dao.getInspectionQuestionAnswers(inspectionId)[0]
            val viewModel = InspectionViewModel(applicationContext)
            viewModel.submitInspection(inspection).observe(this, { result ->
                if (result != null) {
                    Toast.makeText(this, "Inspection Submitted!", Toast.LENGTH_SHORT).show()
                    dao.deleteInspection(inspection.inspection)
                    setReminder()
                    back()
                    finish()
                }
            })
        } else {
            Toast.makeText(
                this,
                "You need internet connection to submit the inspection",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun setReminder() {
        val inspection = inspectionInfo.inspection
        val inspectionData = dao.getInspectionData(inspection.location, inspection.type)[0]
        val currentTime = System.currentTimeMillis()
        val reminder = InspectionReminder(
            prefs!!.getString(SharedKeys.USERNAME)!!,
            inspectionData.period,
            inspectionData.location,
            inspectionData.type,
            currentTime
        )
        dao.insertReminder(reminder)
    }
}