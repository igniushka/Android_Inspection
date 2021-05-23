package activity

import activity.databinding.ActivityMainBinding
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import api.InspectionViewModel
import api.ResponseBean
import db.database.StaticDataInitializer
import shared.SharedKeys
import shared.SharedPreferenceWriter


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var viewModel: InspectionViewModel
    private lateinit var binding: ActivityMainBinding
    private var prefs: SharedPreferenceWriter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StaticDataInitializer().initialize(applicationContext)
        viewModel = InspectionViewModel(applicationContext)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.loginBtn.setOnClickListener(this)
        binding.registerBtn.setOnClickListener(this)
        prefs = SharedPreferenceWriter.getInstance(applicationContext)
        if (prefs?.getString(SharedKeys.LOGGED_IN).equals(SharedKeys.TRUE, ignoreCase = true)) {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }

    private fun inputValid(): Boolean {
        if (binding.usernameEdit.text.isEmpty()) {
            Toast.makeText(applicationContext, "Enter a username!", Toast.LENGTH_SHORT).show()
            return false
        }
        if (binding.passwordEdit.text.isEmpty()) {
            Toast.makeText(applicationContext, "Enter a password!", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }


    private fun register() {
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    private fun login() {
        if (inputValid()) {
            val username = binding.usernameEdit.text.toString()
            val password = binding.passwordEdit.text.toString()
            viewModel.login(username, password).observe(this, Observer { result  ->
                if (result?.token != null) {
                    Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
                    prefs?.writeStringValue(SharedKeys.TOKEN, result.token)
                    prefs?.writeStringValue(SharedKeys.USERNAME, username)
                    prefs?.writeStringValue(SharedKeys.PASSWORD, password)
                    prefs?.writeStringValue(SharedKeys.LOGGED_IN, SharedKeys.TRUE)
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                }
            })
        }
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.register_btn -> register()
                R.id.login_btn -> login()
            }
        }

    }


}



