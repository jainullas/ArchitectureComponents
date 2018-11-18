package com.jain.ullas.room.ui

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.jain.ullas.room.R
import com.jain.ullas.room.database.AppDatabase
import com.jain.ullas.room.database.Task
import com.jain.ullas.room.database.TaskDao
import kotlinx.android.synthetic.main.activity_tasks.*
import kotlinx.android.synthetic.main.content_tasks.*
import kotlin.concurrent.thread

class TasksActivity : AppCompatActivity() {

    private lateinit var database : AppDatabase
    private lateinit var taskDao : TaskDao
    private lateinit var taskListAdapter: TaskListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)
        setSupportActionBar(toolbar)

        database = AppDatabase.getInstance(this)
        taskDao = database.dao()
        taskListAdapter = TaskListAdapter()
        tasksList.apply {
            adapter = taskListAdapter
            layoutManager = LinearLayoutManager(this@TasksActivity)
        }
        refreshAllTasks()
        fab.setOnClickListener {
            addTask()
        }


    }

    private fun refreshAllTasks() {
        thread {
            val allTasks = taskDao.getAll()
            runOnUiThread {
                allTasks.forEach { taskListAdapter.addTask(it) }
            }

        }
    }

    private fun addTask() {
        val title = taskTitleInput.text.toString()
        if(title.isBlank()){
            Snackbar.make(toolbar, "Task title is required", Snackbar.LENGTH_SHORT).show()
            return
        }
        thread {
            taskDao.insert(Task(title = title))
            refreshAllTasks()
        }
        taskTitleInput.text.clear()
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu_overflow_delete, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when(item.itemId){
//            R.id.
//        }
//    }


}
