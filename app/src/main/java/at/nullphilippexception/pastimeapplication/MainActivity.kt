package at.nullphilippexception.pastimeapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import at.nullphilippexception.pastimeapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewModel: MainActivityViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private val SCALE_FACTOR = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.uiData.observe(this) { hobby ->
            binding.tvActivity.text = hobby.activity
            binding.tvType.text = hobby.type
            binding.tvParticipants.text = hobby.participants.toString()
            binding.tvLink.text = hobby.link
            binding.pbPrice.progress = (hobby.price!! * SCALE_FACTOR).toInt()
            binding.pbAccessibility.progress = (hobby.accessibility!! * SCALE_FACTOR).toInt()
        }

        viewModel.viewModelEvent.observe(this) { msg ->
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
        }

        binding.spActivityCategory.adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_item,
            EHobbyTypes.values())

        binding.btnGetNextActivity.setOnClickListener {
            viewModel.fetchHobbyData(
                EHobbyTypes.valueOf(binding.spActivityCategory.selectedItem.toString())
            )
        }
    }
}