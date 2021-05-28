package activity

import activity.databinding.RemindersBinding
import adapter.ReminderAdapter
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import db.database.DatabaseManager
import shared.SharedKeys
import shared.SharedPreferenceWriter

class RemindersActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: RemindersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.reminders)
        binding.back.setOnClickListener(this)
        val prefs = SharedPreferenceWriter.getInstance(applicationContext)
        val dao = DatabaseManager.getInstance(applicationContext).getInspectionDAO()
        val username = prefs!!.getString(SharedKeys.USERNAME)!!
        val reminders = dao.getReminders(username)
        val reminderAdapter = ReminderAdapter(reminders, this, username)
        val linearLayoutManager = LinearLayoutManager(applicationContext)
        binding.inspectionRecycler.layoutManager = linearLayoutManager
        binding.inspectionRecycler.adapter = reminderAdapter

    }


    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.back -> onBackPressed()
            }
        }
    }
}