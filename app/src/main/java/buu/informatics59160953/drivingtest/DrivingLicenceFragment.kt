package buu.informatics59160953.drivingtest


import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import buu.informatics59160953.drivingtest.databinding.FragmentDrivingLicenceBinding
import kotlinx.android.synthetic.main.fragment_driving_licence.*
import kotlin.math.log

/**
 * A simple [Fragment] subclass.
 */
class DrivingLicenceFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentDrivingLicenceBinding>(inflater,
            R.layout.fragment_driving_licence,container,false)


        binding.testRankButton.setOnClickListener{ view ->
            view.findNavController().navigate(R.id.action_drivingLicence_to_testRank)
        }

        binding.examGuideButton.setOnClickListener{ view ->
            view.findNavController().navigate(R.id.action_drivingLicence_to_examGuidelines)
        }

        Log.d("application","application started")
        setHasOptionsMenu(true)
        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
//        inflater?.inflate(R.menu.menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,
            view!!.findNavController()) || super.onOptionsItemSelected(item)
    }


}
