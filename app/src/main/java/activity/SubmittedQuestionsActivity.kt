package activity

import activity.databinding.QuestionBinding
import adapter.SubmittedAnswerAdapter
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import api.InspectionViewModel
import api.bean.AnswerBean
import api.bean.SubmittedInspectionBean
import shared.SharedKeys

class SubmittedQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: QuestionBinding
    private lateinit var viewModel: InspectionViewModel
    private var inspectionId: Long = -1
    private var inspectionInfo: SubmittedInspectionBean? = null


    private var questionNo = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = InspectionViewModel(applicationContext)
        binding = DataBindingUtil.setContentView(this, R.layout.question)
        binding.prevQuestion.setOnClickListener(this)
        binding.nextQuestion.setOnClickListener(this)
        binding.back.setOnClickListener(this)
        binding.naCheckbox.isEnabled = false
        binding.completeInspection.visibility = View.GONE
        binding.submitInspection.visibility = View.GONE
        binding.progressBarCyclic.visibility = View.VISIBLE
        inspectionId = intent.extras!!.getLong(SharedKeys.INSPECTION_ID)
        viewModel.getInspectionInfo(inspectionId).observe(this, { result ->
            binding.progressBarCyclic.visibility = View.GONE
            result?.let {
                inspectionInfo = result.inspection
                val inspectionLabel = inspectionInfo?.location + " " + inspectionInfo?.type
                binding.inspectionLabel.text = inspectionLabel
                setQuestionData()

            }
        })
    }

    private fun setQuestionData() {
        val question = inspectionInfo?.questions?.get(questionNo)
        binding.questionText.text = question?.question
        binding.naCheckbox.isChecked = question?.notApplicable == 1
        if (question != null) {
            question.answers?.let { setAnswers(it) }
        }

        if (questionNo == 0) {
            setPrevButton(true)
        } else {
            setPrevButton(false)
        }
        if (questionNo == inspectionInfo?.questions?.lastIndex) {
            setNextButton(true)
        } else {
            setNextButton(false)
        }
    }

    private fun setAnswers(answers: List<AnswerBean>) {
        val answersAdapter = SubmittedAnswerAdapter(answers)
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
                R.id.back -> back()
            }
        }
    }

    override fun onBackPressed() {
        back()
    }

    private fun back() {
        startActivity(
            Intent(
                this,
                SubmittedInspectionActivity::class.java
            )
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
}