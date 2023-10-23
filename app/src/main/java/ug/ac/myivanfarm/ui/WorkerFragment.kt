package ug.ac.myivanfarm.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ug.ac.myivanfarm.R
import ug.ac.myivanfarm.data.retrofit.WorkerRetrofitInstance
import ug.ac.myivanfarm.databinding.FragmentWorkerBinding
import ug.ac.myivanfarm.ui.adapter.WorkerAdapter

class WorkerFragment : Fragment() {
    private var _binding: FragmentWorkerBinding? = null
    private val binding get() = _binding!!

    //    private lateinit var viewModel: WorkerViewModel
    val viewModel: WorkerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWorkerBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.btnUpdateList.setOnClickListener() {
            binding.recycler.visibility = View.GONE
            binding.floatingActionButton.visibility = View.GONE
            Toast.makeText(
                requireContext(),
                "Downloading workers from the server",
                Toast.LENGTH_LONG
            ).show()
            CoroutineScope(Dispatchers.IO).launch {
                val retrofit = viewModel.downloadRemoteWorkers(requireContext())
                retrofit
                withContext(Dispatchers.Main) {
                    Log.d("Worker", "$retrofit")
                    Toast.makeText(
                        requireContext(),
                        "Downloading workers from the server",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }


        binding.btnDisplayList.setOnClickListener() {
            val recyclerview = binding.recycler
            recyclerview.visibility = View.VISIBLE

            binding.floatingActionButton.visibility = View.VISIBLE


            CoroutineScope(Dispatchers.IO).launch {
                try{
                    val room = viewModel.getAllDbWorkers(requireContext()).collect{
                        if(it != null){
                            withContext(Dispatchers.Main){
                                Toast.makeText(
                                    requireContext(),
                                    "Getting workers from Room",
                                    Toast.LENGTH_LONG
                                ).show()
                                val adapter = WorkerAdapter(requireContext(), it)
                                Log.d("workerView","in workers try")
                                recyclerview.layoutManager = LinearLayoutManager(context)
                                recyclerview.adapter = adapter
                            }
                        }
                    }
           
                } catch(e: Throwable){
                    withContext(Dispatchers.Main){
                        Toast.makeText(
                            requireContext(),
                            "Error occurred  $e",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }

        binding.floatingActionButton.setOnClickListener() {
            findNavController().navigate(WorkerFragmentDirections.actionWorkerNavToWorkerAddNav())
        }
        return view



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

//    override fun onDestroy() {
//        super.onDestroy()
//    }

}