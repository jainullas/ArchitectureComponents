package com.jain.ullas.architecturecomponents

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jain.ullas.room.ui.TasksActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startActivity(Intent(this, TasksActivity::class.java))
    }
}
