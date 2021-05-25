package adapter

import activity.QuestionsActivity
import activity.R
import activity.databinding.InspectionBinding
import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import db.entity.Inspection
import shared.SharedKeys


class ContinueInspectionAdapter(
    private val inspections: List<Inspection>,
    private val activity: Activity
) :
    RecyclerView.Adapter<InspectionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InspectionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem: InspectionBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.inspection, parent, false)
        return InspectionViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: InspectionViewHolder, position: Int) {
        val inspection = inspections[position]
        holder.binding.user.text = inspection.user
        holder.binding.location.text = inspection.location
        holder.binding.type.text = inspection.type
        holder.binding.completed.text = inspection.completed.toString()
        holder.binding.inspection.setOnClickListener {
            activity.startActivity(Intent(activity, QuestionsActivity::class.java).putExtra(SharedKeys.INSPECTION_ID, inspection.id))
            activity.finish()
        }

    }

    override fun getItemCount(): Int {
        return inspections.size
    }
}

class InspectionViewHolder(val binding: InspectionBinding) : ViewHolder(binding.root)