package com.jain.ullas.room.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jain.ullas.room.R
import com.jain.ullas.room.database.Task
import com.jain.ullas.room.ui.TaskListAdapter.TaskViewHolder
import kotlinx.android.synthetic.main.item_task_row.view.*

class TaskListAdapter(val onTaskClickListener : (Int) -> Unit) : RecyclerView.Adapter<TaskViewHolder>() {

    private val tasks : MutableList<Task>  = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, position: Int) =
        TaskViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_task_row, parent, false))

    override fun getItemCount(): Int = tasks.size

    override fun onBindViewHolder(viewHolder: TaskViewHolder, position: Int) {
        viewHolder.bind(tasks[position])
    }

    fun addTask(task: Task){
        if(!tasks.contains(task)){
            tasks.add(task)
            notifyItemInserted(tasks.size)
        }
    }


    inner class TaskViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(task: Task){
            itemView.taskTitle.text = task.title
            itemView.setOnClickListener {
                onTaskClickListener.invoke(task.id)
            }
        }
    }

}