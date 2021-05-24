package activity

import activity.databinding.NewInspectionBinding
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import db.database.*
import shared.SharedPreferenceWriter


class NewInspectionActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: NewInspectionBinding
    private var prefs: SharedPreferenceWriter? = null
    private val locations = arrayOf(LOCATION_A, LOCATION_B, LOCATION_C)
    private val types = arrayOf(TYPE_1, TYPE_2, TYPE_3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefs = SharedPreferenceWriter.getInstance(applicationContext)
        binding = DataBindingUtil.setContentView(this, R.layout.new_inspection)
        binding.newInspection.setOnClickListener(this)
        binding.back.setOnClickListener(this)
        val locationAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, locations)
        val typeAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, types)
        binding.locationDropdown.adapter = locationAdapter
        binding.typeDropdown.adapter = typeAdapter
    }

    private fun createNewInspection(){
        binding.progressBarCyclic.visibility = View.VISIBLE

        val dao = DatabaseManager.getInstance(applicationContext).getInspectionDAO()


    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.new_inspection -> createNewInspection()
                R.id.back -> onBackPressed()
            }
        }

    }


}