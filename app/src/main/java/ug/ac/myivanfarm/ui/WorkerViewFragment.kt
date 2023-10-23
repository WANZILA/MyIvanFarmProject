package ug.ac.myivanfarm.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import ug.ac.myivanfarm.R
import ug.ac.myivanfarm.databinding.FragmentWorkerViewBinding

class WorkerViewFragment : Fragment() {

    private lateinit var viewModel: WorkerViewViewModel
    private var  _binding: FragmentWorkerViewBinding?= null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //when using bindig
        _binding = FragmentWorkerViewBinding.inflate(layoutInflater)
        val name = binding.txtTitle

        val fab = binding.floatingActionButton

        fab.setOnClickListener(){
//            findNavController().navigate(R.id.action_workerViewNav_to_workerAddNav)
        }
        return binding.root
        //when usin views
//        val inflater = inflater.inflate(R.layout.fragment_worker_view, container, false)
//        val name = inflater.findViewById<TextView>(R.id.txt_title)
//        return inflater
//        return inflater.inflate(R.layout.fragment_worker_view, container, false)
       // return inflater.inflate(R.layout.fragment_worker_view, container, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}