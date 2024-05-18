package com.example.recyclersecond

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclersecond.R
import com.google.android.material.snackbar.Snackbar

private lateinit var recyclerView: RecyclerView
private lateinit var adapter: TaskAdapter
private lateinit var layoutManager: LinearLayoutManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.btn)
        val etTask = findViewById<EditText>(R.id.etTask)
        val tasksExample = mutableListOf(
            Task("Here you can", false), Task("Write your goals!", true)
        )

        recyclerView = findViewById(R.id.recyclerView)
        adapter = TaskAdapter(tasksExample)
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        btn.setOnClickListener {
            val newTaskContent = etTask.text.toString()
            val newTask = Task(newTaskContent, false)
            tasksExample.add(newTask)
            adapter.notifyItemInserted(tasksExample.size - 1)
        }

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val deletedTask: Task = tasksExample.get(viewHolder.adapterPosition)

                val position = viewHolder.adapterPosition
                tasksExample.removeAt(position)
                adapter.notifyItemRemoved(position)
            }
        }).attachToRecyclerView(recyclerView)
    }
}