package com.android.swordgrouptask.ui.fragment

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.swordgrouptask.R
import com.android.swordgrouptask.app.StudentApp
import com.android.swordgrouptask.databinding.FragmentListBinding
import com.android.swordgrouptask.room.Student
import com.android.swordgrouptask.ui.adapter.StudentListAdapter
import com.android.swordgrouptask.viewmodel.StudentViewModel
import com.android.swordgrouptask.viewmodel.StudentViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.flow.collectLatest

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private val viewModel: StudentViewModel by activityViewModels {
        StudentViewModelFactory((activity?.application as StudentApp).database.studentDao())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val studentListAdapter = StudentListAdapter { from, student ->
            if (from == "Edit") {
                callNavigation(student)
            } else {
                MaterialAlertDialogBuilder(requireActivity())
                    .setTitle(getString(R.string.alert))
                    .setMessage(getString(R.string.are_you_sure) + " ${student.name}?")
                    .setPositiveButton(getString(R.string.ok)) { _, _ ->
                        viewModel.deleteStudent(student)
                    }
                    .setNegativeButton(getString(R.string.cancel)) { _, _ ->

                    }
                    .show()
            }
        }

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            rvStudentList.layoutManager = LinearLayoutManager(requireActivity())
            rvStudentList.adapter = studentListAdapter

            fabAddStudent.setOnClickListener {
                val student = Student(name = "", age = "", mClass = "")
                callNavigation(student)
            }
        }

        lifecycleScope.launchWhenCreated {
            viewModel.list.collectLatest {
                studentListAdapter.submitList(it)
            }
        }
    }

    private fun callNavigation(student: Student) {
        val action = ListFragmentDirections.actionListFragmentToAddEditFragment(student)
        findNavController().navigate(action)
    }
}