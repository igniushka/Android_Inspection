package activity

import activity.databinding.ContinueInspectionBinding
import adapter.ContinueInspectionAdapter
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import db.database.DatabaseManager
import shared.SharedKeys
import shared.SharedPreferenceWriter

class ContinueInspectionActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ContinueInspectionBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.continue_inspection);
        binding.back.setOnClickListener(this)
        val prefs = SharedPreferenceWriter.getInstance(applicationContext)
        val dao = DatabaseManager.getInstance(applicationContext).getInspectionDAO()
        val username = prefs!!.getString(SharedKeys.USERNAME)
        val inspections = dao.getUserInspections(username!!, false)
        val inspectionsAdapter = ContinueInspectionAdapter(inspections, this)
        val linearLayoutManager = LinearLayoutManager(applicationContext)
        binding.inspectionRecycler.layoutManager = linearLayoutManager
        binding.inspectionRecycler.adapter = inspectionsAdapter
    }


    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.back -> onBackPressed()
            }
        }
    }
}