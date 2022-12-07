package com.android.swordgrouptask.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.android.swordgrouptask.R
import com.android.swordgrouptask.app.StudentApp
import com.android.swordgrouptask.databinding.FragmentAddEditBinding
import com.android.swordgrouptask.room.Student
import com.android.swordgrouptask.viewmodel.StudentViewModel
import com.android.swordgrouptask.viewmodel.StudentViewModelFactory

class AddEditFragment : Fragment() {

    private lateinit var binding: FragmentAddEditBinding
    private val args: AddEditFragmentArgs by navArgs()
    private val viewModel: StudentViewModel by activityViewModels {
        StudentViewModelFactory((activity?.application as StudentApp).database.studentDao())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).supportActionBar?.title =
            if (args.student.name.isEmpty()) getString(R.string.add_student) else getString(R.string.edit_student)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            if (args.student.name.isNotEmpty()) {
                student = args.student
            }

            tilStudentName.editText?.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    tilStudentName.error = null
                }

                override fun afterTextChanged(p0: Editable?) {

                }
            })
            tilStudentAge.editText?.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    tilStudentAge.error = null
                }

                override fun afterTextChanged(p0: Editable?) {

                }
            })
            tilStudentClass.editText?.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    tilStudentClass.error = null
                }

                override fun afterTextChanged(p0: Editable?) {

                }
            })
            btnSubmit.setOnClickListener {
                val name = tilStudentName.editText?.text.toString().trim()
                val age = tilStudentAge.editText?.text.toString().trim()
                val mClass = tilStudentClass.editText?.text.toString().trim()
                if (name.isEmpty()) {
                    tilStudentName.error = getString(R.string.please_enter_name)
                } else if (age.isEmpty()) {
                    tilStudentName.error = getString(R.string.please_enter_age)
                } else if (mClass.isEmpty()) {
                    tilStudentName.error = getString(R.string.please_enter_class)
                } else {
                    if (args.student.name.isEmpty()) {
                        val student = Student(name = name, age = age, mClass = mClass)
                        viewModel.insertStudent(student)
                    } else {
                        val student = Student(
                            rollNo = args.student.rollNo,
                            name = name,
                            age = age,
                            mClass = mClass
                        )
                        viewModel.updateStudent(student)
                    }
                    findNavController().popBackStack()
                }
            }
        }
    }
}