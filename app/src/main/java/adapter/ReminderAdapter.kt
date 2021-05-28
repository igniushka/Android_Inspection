package adapter

import activity.QuestionsActivity
import activity.R
import activity.databinding.InspectionBinding
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import db.database.DatabaseManager
import db.entity.Answer
import db.entity.Inspection
import db.entity.InspectionReminder
import db.entity.Question
import shared.SharedKeys

const val MILLISECONDS_IN_HOUR = 3600000
const val HOURS_IN_DAY = 24


class ReminderAdapter(
    private val reminders: List<InspectionReminder>,
    private val activity: Activity,
    private val username: String

) :
    RecyclerView.Adapter<ReminderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReminderViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem: InspectionBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.inspection, parent, false)
        return ReminderViewHolder(listItem)
    }


    override fun onBindViewHolder(holder: ReminderViewHolder, position: Int) {
        val reminder = reminders[position]
        holder.binding.user.text = reminder.user
        holder.binding.location.text = reminder.location
        holder.binding.type.text = reminder.type
        val currentTime = System.currentTimeMillis()
        val timeDiff = currentTime - reminder.date
        val hoursLeft = reminder.period - timeDiff / MILLISECONDS_IN_HOUR
        if (hoursLeft < 0){
            holder.binding.completed.text = "ASAP!"
        } else {
            val dayDiff = hoursLeft / HOURS_IN_DAY
            val hourMod = hoursLeft % HOURS_IN_DAY
            if (dayDiff == 0L){
                holder.binding.completed.text = "$hoursLeft h."
            } else {
                holder.binding.completed.text = "$dayDiff d. $hourMod h."
            }
        }


        holder.binding.row.setOnClickListener {
            showCreateInspectionAlert(reminder.location, reminder.type)
        }
    }


    private fun showCreateInspectionAlert(location: String, type: String) {
        AlertDialog.Builder(activity)
            .setTitle("Please Confirm")
            .setMessage("Are you sure you want to start this inspection?")
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setPositiveButton(
                "Yes"
            ) { _, _ ->
                createNewInspection(location, type)
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }.show()
    }

    private fun createNewInspection(location: String, type: String) {
        val dao = DatabaseManager.getInstance(activity.applicationContext).getInspectionDAO()
        val inspectionQuestionsAnswers = dao.getInspectionQuestionAnswerData(location, type)[0]
        val inspectionData = inspectionQuestionsAnswers.inspectionData
        val newInspection = Inspection(
            username,
            inspectionData.id,
            inspectionData.type,
            inspectionData.location,
            false
        )
        val newInspectionId = dao.insertNewInspection(newInspection)
        for (questionWithAnswers in inspectionQuestionsAnswers.questions) {
            val questionData = questionWithAnswers.questionData
            val newQuestion =
                Question(newInspectionId, questionData.questionDataId, questionData.question, false)
            val questionId = dao.insertNewQuestion(newQuestion)
            for (answerData in questionWithAnswers.answersData) {
                val newAnswer = Answer(questionId, answerData.id, answerData.answer, 0)
                dao.insertNewAnswer(newAnswer)
            }
        }
        activity.startActivity(
            Intent(activity, QuestionsActivity::class.java).putExtra(
                SharedKeys.INSPECTION_ID,
                newInspectionId
            ).putExtra(
                SharedKeys.COMPLETED,
                false
            )
        )
        activity.finish()
    }

    override fun getItemCount(): Int {
        return reminders.size
    }
}

class ReminderViewHolder(val binding: InspectionBinding) : RecyclerView.ViewHolder(binding.root)