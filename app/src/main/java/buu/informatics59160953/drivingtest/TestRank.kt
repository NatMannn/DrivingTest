package buu.informatics59160953.drivingtest


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import buu.informatics59160953.drivingtest.databinding.FragmentResultBinding
import buu.informatics59160953.drivingtest.databinding.FragmentTestRankBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_test_rank.*

/**
 * A simple [Fragment] subclass.
 */
class TestRank : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentTestRankBinding>(inflater,
            R.layout.fragment_test_rank,container,false)
        // Inflate the layout for this fragment
        binding.submitTestButton.setOnClickListener{ view ->
            view.findNavController().navigate(R.id.action_testRank_to_result)
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var snackbar = view?.let { Snackbar.make(it,"เริ่มแบบทดสอบ",Snackbar.LENGTH_LONG) }
        snackbar?.show()
    }




}
