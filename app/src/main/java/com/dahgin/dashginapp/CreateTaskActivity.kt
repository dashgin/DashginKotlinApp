package com.dahgin.dashginapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dahgin.dashginapp.databinding.ActivityCreateTaskBinding
import kotlin.math.min

class CreateTaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCreateTaskBinding.inflate(layoutInflater)
        val root = binding.root
        setContentView(root)

        val year: Int
        val mon: Int
        val day: Int

        val extras = intent.extras
        if (extras != null) {
            year = extras.getInt("year")
            mon = extras.getInt("mon")
            day = extras.getInt("day")
        }

        binding.completeButton.setOnClickListener {
            var title = binding.title.text.toString()
            var description = binding.description.text.toString()
            var hour = binding.timePicker.currentHour
            var minute = binding.timePicker.currentMinute



            if (title.isNotEmpty() && description.isNotEmpty()) {
                val task = Task(title, description, false, 2022, 6, 12, hour, minute)
                Data.tasks.add(task)
                startActivity(Intent(this, MainActivity::class.java))
            } else
                Toast.makeText(this, "Title or Description can't be empty!", Toast.LENGTH_SHORT)
                    .show()


        }
    }
}