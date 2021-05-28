package adapter

import activity.R
import activity.databinding.AnswerBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import api.bean.AnswerBean

class SubmittedAnswerAdapter(
    private val answers: List<AnswerBean>,
) :
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
        value = answer.value!!
        holder.binding.answerText.text = answer.answer
        holder.binding.answerSeekbar.progress = value
        holder.binding.answerValue.text = answer.value.toString()
        holder.binding.answerSeekbar.isEnabled = false
    }

    override fun getItemCount(): Int {
        return answers.size
    }
}