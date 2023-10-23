package ug.ac.myivanfarm.ui

import androidx.lifecycle.ViewModelProvider
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
import ug.ac.myivanfarm.databinding.FragmentPurchaseBinding
import ug.ac.myivanfarm.ui.adapter.PurchaseAdapter
import ug.ac.myivanfarm.ui.adapter.SaleAdapter

class PurchaseFragment : Fragment() {

    private var _binding: FragmentPurchaseBinding ?= null
    val binding get() = _binding!!

    val viewModel: PurchaseViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return inflater.inflate(R.layout.fragment_purchase, container, false)
        _binding = FragmentPurchaseBinding.inflate(inflater,container, false)

        binding.btnUpdateList.setOnClickListener() {
            binding.recycler.visibility = View.GONE
            binding.floatingActionButton.visibility = View.GONE
            Toast.makeText(
                requireContext(),
                "Downloading  from the server",
                Toast.LENGTH_LONG
            ).show()
            CoroutineScope(Dispatchers.IO).launch {
                val retrofit = viewModel.downloadRemoteData(requireContext())

                withContext(Dispatchers.Main) {
                    Log.d("P frag", "$retrofit")
                    Toast.makeText(
                        requireContext(),
                        "Downloading from the server",
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
                    val room = viewModel.getAllDbData(requireContext()).collect{
                        if(it != null){
                            withContext(Dispatchers.Main){
                                Toast.makeText(
                                    requireContext(),
                                    "Getting workers from Room",
                                    Toast.LENGTH_LONG
                                ).show()
                                val adapter = PurchaseAdapter(requireContext(), it)
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

        binding.floatingActionButton.setOnClickListener(){
            findNavController().navigate(PurchaseFragmentDirections.actionPurchaseNavToPurchaseAddFragment())

        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //call the retrofit

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}