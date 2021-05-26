package adapter

import activity.R
import activity.databinding.AnswerBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import db.database.InspectionDAO
import db.entity.Answer


class AnswerAdapter(private val answers: List<Answer>, private val dao: InspectionDAO, private val completed: Boolean) :
    RecyclerView.Adapter<AnswerViewHolder>() {
    private var value: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem: AnswerBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.answer, parent, false)
        return AnswerViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: AnswerViewHolder, position: Int) {
        val answer = answers[position]
        value = answer.value
        holder.binding.answerText.text = answer.answer
        holder.binding.answerSeekbar.progress = answer.value
        holder.binding.answerValue.text = answer.value.toString()
        holder.binding.answerSeekbar.isEnabled = !completed
        holder.binding.answerSeekbar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                holder.binding.answerValue.text = progress.toString()
                value = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                answer.value = value
                dao.updateAnswer(answer)
            }
        })
    }

    override fun getItemCount(): Int {
        return answers.size
    }
}

class AnswerViewHolder(val binding: AnswerBinding) : ViewHolder(binding.root)