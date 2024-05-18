package com.example.recyclersecond

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclersecond.R

class TaskAdapter(var tasks: MutableList<Task>) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        return TaskViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bindTask(tasks[position])
    }

    inner class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvDescription = view.findViewById<TextView>(R.id.tvTask)
        val cbCompleted = view.findViewById<CheckBox>(R.id.cbTask)

        fun bindTask(task: Task) {
            tvDescription.text = task.description
            cbCompleted.isChecked = task.isChecked

            cbCompleted.setOnCheckedChangeListener { buttonView, isChecked ->
                tasks[adapterPosition].isChecked = isChecked
            }
        }
    }
}