package com.jain.ullas.room.ui

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jain.ullas.room.R
import com.jain.ullas.room.database.AppDatabase
import com.jain.ullas.room.database.TaskDao
import kotlinx.android.synthetic.main.activity_task_detail.*

class TaskDetailActivity : AppCompatActivity() {

    companion object {
        const val TASK_ID = "task_id"
    }

    private lateinit var taskDao: TaskDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        taskDao = AppDatabase.getInstance(this).dao()

        intent.extras.getInt(TASK_ID).let {
            taskDao.getTask(it).observe(this@TaskDetailActivity, Observer {
                details.text = it?.title.plus(" ").plus(it)
            })
        }
    }
}
