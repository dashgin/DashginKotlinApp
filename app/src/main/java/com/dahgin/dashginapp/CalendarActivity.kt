package com.dahgin.dashginapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dahgin.dashginapp.databinding.ActivityCalendarBinding
import java.util.*


class CalendarActivity : AppCompatActivity() {
    var year: Int? = null
    var mon: Int? = null
    var day: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        val binding = ActivityCalendarBinding.inflate(layoutInflater)
        val root = binding.root
        setContentView(root)


        binding.date.text = "TIME"

        binding.calendarView.setOnDateChangeListener { calendarView, year, mon, day ->
            this.year = year
            this.mon = mon
            this.day = day
            binding.date.text = "OKAY"
        }

        binding.createButton.setOnClickListener {

            if (year != null || mon != null || day != null)
                goScheduleTaskScreen(year!!, mon!!, day!!)
            else
                Toast.makeText(this, "Please choose date!", Toast.LENGTH_SHORT).show()
            
        }
    }

    private fun goScheduleTaskScreen(year: Int, mon: Int, day: Int) {
        val k = Intent(this, CreateTaskActivity::class.java)
        k.putExtra("year", year)
        k.putExtra("mon", mon)
        k.putExtra("day", day)
        startActivity(k)

    }

}
