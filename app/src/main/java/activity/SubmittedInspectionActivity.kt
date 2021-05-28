package activity

import activity.databinding.ContinueInspectionBinding
import adapter.SubmittedInspectionAdapter
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import api.InspectionViewModel

class SubmittedInspectionActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ContinueInspectionBinding
    private lateinit var viewModel: InspectionViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = InspectionViewModel(applicationContext)
        binding = DataBindingUtil.setContentView(this, R.layout.continue_inspection)
        binding.back.setOnClickListener(this)
        binding.completed.text = "Completed on"
        binding.continueInspectionText.text = "Select submitted inspection to view"
        binding.progressBarCyclic.visibility = View.VISIBLE
        viewModel.getUserInspections().observe(this, { result ->
            binding.progressBarCyclic.visibility = View.GONE
            result?.let { res ->
                val inspections = res.inspections
                val inspectionsAdapter = inspections?.let {
                    SubmittedInspectionAdapter(it, this)
                }
                val linearLayoutManager = LinearLayoutManager(applicationContext)
                binding.inspectionRecycler.layoutManager = linearLayoutManager
                binding.inspectionRecycler.adapter = inspectionsAdapter
            }
        })
    }


    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.back -> onBackPressed()
            }
        }
    }


}