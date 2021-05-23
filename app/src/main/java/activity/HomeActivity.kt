package activity

import activity.databinding.HomeBinding
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import api.InspectionViewModel
import shared.SharedPreferenceWriter

class HomeActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: HomeBinding
    private lateinit var viewModel: InspectionViewModel
    private var prefs: SharedPreferenceWriter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = InspectionViewModel(applicationContext)
        prefs = SharedPreferenceWriter.getInstance(applicationContext)
        binding = DataBindingUtil.setContentView(this, R.layout.home);
        binding.newInspection.setOnClickListener(this)
        binding.continueInspection.setOnClickListener(this)
        binding.history.setOnClickListener(this)
        binding.schedule.setOnClickListener(this)
        binding.logOut.setOnClickListener(this)
    }

    private fun createNewInspection() {
        viewModel.verify().observe(this, Observer { result ->
            result?.message?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun logout() {
        prefs?.clearPreferenceValues()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.new_inspection -> createNewInspection()
                R.id.log_out -> logout()
//                R.id.continue_inspection ->
//                R.id.history ->
//                R.id.schedule ->

            }
        }

    }
}