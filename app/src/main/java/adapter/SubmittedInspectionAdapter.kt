package adapter
import activity.R
import activity.databinding.InspectionBinding
import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import api.bean.SubmittedInspectionBean
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class SubmittedInspectionAdapter(
    private val inspections: List<SubmittedInspectionBean>,
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
        val correctedDate = fixDate(inspection.date)
        holder.binding.completed.text = correctedDate
        holder.binding.row.setOnClickListener {
//            activity.startActivity(
//                Intent(activity, QuestionsActivity::class.java).putExtra(
//                    SharedKeys.INSPECTION_ID,
//                    inspection.id
//                ).putExtra(
//                    SharedKeys.COMPLETED,
//                    completed
//                )
//            )
//            activity.finish()
        }
    }

    private fun fixDate(time: String?): String? {
        val inputPattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
        val outputPattern = "yyyy-MM-dd \n HH:mm:ss"
        val inputFormat = SimpleDateFormat(inputPattern)
        val outputFormat = SimpleDateFormat(outputPattern)
        val date: Date?
        var str: String?
        try {
            date = inputFormat.parse(time)
            str = outputFormat.format(date)
        } catch (ignore: ParseException) {
            str = time
        }
        return str
    }

    override fun getItemCount(): Int {
        return inspections.size
    }
}