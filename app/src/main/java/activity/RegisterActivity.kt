package activity

import activity.databinding.RegisterBinding
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import api.InspectionViewModel

class RegisterActivity : AppCompatActivity(), View.OnClickListener {


    private lateinit var binding: RegisterBinding
    private lateinit var viewModel: InspectionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = InspectionViewModel(applicationContext)
        binding = DataBindingUtil.setContentView(this, R.layout.register)
        binding.registerBtn.setOnClickListener(this)
        binding.loginBtn.setOnClickListener(this)

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
        if (binding.repeatPasswordEdit.text.isEmpty()) {
            Toast.makeText(applicationContext, "Repeat the password!", Toast.LENGTH_SHORT).show()
            return false
        }
        if (binding.repeatPasswordEdit.text.toString() != binding.passwordEdit.text.toString()) {
            Toast.makeText(applicationContext, "Passwords must match!", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun register() {
        if (inputValid()) {
            val username = binding.usernameEdit.text.toString()
            val password = binding.passwordEdit.text.toString()
            viewModel.register(username, password).observe(this, Observer{ result ->
                if (result != null) {
                    result.message.let {
                        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                    }
                    onBackPressed()
                }
            })
        }
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.register_btn -> register()
                R.id.login_btn -> onBackPressed()
            }
        }

    }
}