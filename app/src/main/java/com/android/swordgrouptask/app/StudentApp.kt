package com.android.swordgrouptask.app

import android.app.Application
import com.android.swordgrouptask.room.StudentDatabase

class StudentApp : Application() {
    val database: StudentDatabase by lazy {
        StudentDatabase.getDatabase(this)
    }
}