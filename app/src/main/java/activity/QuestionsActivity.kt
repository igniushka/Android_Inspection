package activity

import activity.databinding.NewInspectionBinding
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import shared.SharedPreferenceWriter

class QuestionsActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: NewInspectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

}