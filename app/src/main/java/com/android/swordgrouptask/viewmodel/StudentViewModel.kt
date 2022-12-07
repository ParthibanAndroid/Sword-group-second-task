package com.android.swordgrouptask.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.swordgrouptask.room.Student
import com.android.swordgrouptask.room.StudentDAO
import kotlinx.coroutines.launch

class StudentViewModel(private val studentDAO: StudentDAO) : ViewModel() {

    val list = studentDAO.getAllStudents()

    fun insertStudent(student: Student) {
        viewModelScope.launch {
            studentDAO.insertStudent(student)
        }
    }

    fun updateStudent(student: Student) {
        viewModelScope.launch {
            studentDAO.updateStudent(student)
        }
    }

    fun deleteStudent(student: Student) {
        viewModelScope.launch {
            studentDAO.deleteStudent(student)
        }
    }
}