package com.dahgin.dashginapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.dahgin.dashginapp.databinding.ActivityMainBinding
import com.dahgin.dashginapp.databinding.TaskCardBinding
import java.time.LocalTime


class MainActivity : AppCompatActivity() {
    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelId = "id.notifications"
    private val description = "my notification"
    var id = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val root = binding.root
        setContentView(root)

        binding.fab.setOnClickListener {
            startActivity(Intent(this, CalendarActivity::class.java))
        }
        pushNotf()
        val tasksView = binding.tasksContainer
        Data.tasks.forEach { task ->
            val cardBinding = TaskCardBinding.inflate(layoutInflater)
            cardBinding.title.text = task.title
            cardBinding.description.text = task.description
            cardBinding.time.text = LocalTime.of(task.hour, task.min).toString()
            setNotification(task)
            tasksView.addView(cardBinding.root)
        }
    }

    private fun pushNotf() {
        Handler().postDelayed({
            notificationManager.notify(id++, builder.build())
            pushNotf()
        },25000)
    }

    private fun setNotification(task: Task) {
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.GREEN
            notificationChannel.enableVibration(false)
            notificationManager.createNotificationChannel(notificationChannel)

            builder = Notification.Builder(this, channelId)
                .setContentTitle(task.title)
                .setContentText(task.description)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setLargeIcon(
                    BitmapFactory.decodeResource(
                        this.resources,
                        R.drawable.ic_launcher_foreground
                    )
                )
                .setContentIntent(pendingIntent)

        } else {

            builder = Notification.Builder(this)
                .setContentTitle(task.title)
                .setContentText(task.description)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setLargeIcon(
                    BitmapFactory.decodeResource(
                        this.resources,
                        R.drawable.ic_launcher_foreground
                    )
                ).setContentIntent(pendingIntent)
        }
    }


}