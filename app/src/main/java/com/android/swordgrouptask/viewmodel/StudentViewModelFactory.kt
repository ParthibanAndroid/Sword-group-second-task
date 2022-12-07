package com.android.swordgrouptask.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.swordgrouptask.room.StudentDAO

class StudentViewModelFactory(private val studentDAO: StudentDAO) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StudentViewModel::class.java)) {
            return StudentViewModel(studentDAO) as T
        }
        throw IllegalStateException("View Model not found")
    }
}