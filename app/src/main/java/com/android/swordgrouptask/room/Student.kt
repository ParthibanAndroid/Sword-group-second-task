package com.android.swordgrouptask.room

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Student(
    @PrimaryKey(autoGenerate = true) val rollNo: Int = 0,
    val name: String,
    val age: String,
    val mClass: String
) : Parcelable