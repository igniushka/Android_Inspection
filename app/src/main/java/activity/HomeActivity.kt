package activity

import activity.databinding.HomeBinding
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
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

    private fun startNewInspectionActivity() {
        startActivity(Intent(this, NewInspectionActivity::class.java))
    }

    private fun logout() {
        prefs?.clearPreferenceValues()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.new_inspection -> startNewInspectionActivity()
                R.id.log_out -> logout()
//                R.id.continue_inspection ->
//                R.id.history ->
//                R.id.schedule ->

            }
        }

    }
}