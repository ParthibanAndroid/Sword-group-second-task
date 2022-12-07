package com.android.swordgrouptask.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.swordgrouptask.databinding.AdapterStudentListBinding
import com.android.swordgrouptask.room.Student

class StudentListAdapter(private val onClick: (String, Student) -> Unit) :
    ListAdapter<Student, StudentListAdapter.ViewHolder>(DiffCallback) {
    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<Student>() {
            override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
                return oldItem.rollNo == newItem.rollNo
            }

            override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
                return oldItem == newItem
            }
        }
    }

    class ViewHolder(private val binding: AdapterStudentListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(student: Student, onClick: (String, Student) -> Unit) {
            binding.student = student

            binding.ivEdit.setOnClickListener {
                onClick("Edit", student)
            }
            binding.ivDelete.setOnClickListener {
                onClick("Delete", student)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            AdapterStudentListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onClick)
    }
}