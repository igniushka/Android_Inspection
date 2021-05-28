package activity

import activity.databinding.HomeBinding
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import api.InspectionViewModel
import shared.SharedKeys
import shared.SharedPreferenceWriter
import util.NetworkUtils


class HomeActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: HomeBinding
    private lateinit var viewModel: InspectionViewModel
    private var prefs: SharedPreferenceWriter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = InspectionViewModel(applicationContext)
        prefs = SharedPreferenceWriter.getInstance(applicationContext)
        binding = DataBindingUtil.setContentView(this, R.layout.home)
        val username = prefs!!.getString(SharedKeys.USERNAME)
        "Welcome $username!".also { binding.helloText.text = it }
        binding.newInspection.setOnClickListener(this)
        binding.continueInspection.setOnClickListener(this)
        binding.history.setOnClickListener(this)
        binding.schedule.setOnClickListener(this)
        binding.logOut.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.new_inspection -> startNewInspectionActivity()
                R.id.log_out -> logout()
                R.id.continue_inspection -> launchContinueInspectionActivity()
                R.id.history -> launchViewCompletedInspectionActivity()
                R.id.schedule -> showSchedule()
            }
        }
    }

    private fun startNewInspectionActivity() {
        startActivity(Intent(this, NewInspectionActivity::class.java))
    }

    private fun logout() {
        prefs?.clearPreferenceValues()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun launchContinueInspectionActivity() {
        startActivity(Intent(this, InspectionListActivity::class.java).putExtra(SharedKeys.COMPLETED, SharedKeys.FALSE))
    }

    private fun launchViewCompletedInspectionActivity() {
        startActivity(Intent(this, InspectionListActivity::class.java).putExtra(SharedKeys.COMPLETED, SharedKeys.TRUE))
    }

    private fun showSchedule(){

    }
}