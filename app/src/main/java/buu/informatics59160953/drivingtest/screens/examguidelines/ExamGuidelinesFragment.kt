package buu.informatics59160953.drivingtest.screens.examguidelines


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import buu.informatics59160953.drivingtest.R
import buu.informatics59160953.drivingtest.databinding.FragmentExamGuidelinesBinding

/**
 * A simple [Fragment] subclass.
 */
class ExamGuidelinesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentExamGuidelinesBinding>(inflater,
            R.layout.fragment_exam_guidelines,container,false)

        val viewModel = ViewModelProviders.of(this).get(ExamGuidelinesViewModel::class.java)

        val adapter = ExamGuidelinesAdapter()
        adapter.replaceItems(viewModel.items)

        binding.recyclerview.adapter = adapter

        return binding.root
    }




}
