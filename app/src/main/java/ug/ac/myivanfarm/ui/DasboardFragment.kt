package ug.ac.myivanfarm.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import ug.ac.myivanfarm.R
import ug.ac.myivanfarm.databinding.FragmentDasboardBinding

class DasboardFragment : Fragment() {

    var _binding: FragmentDasboardBinding  ?= null
    val binding get() = _binding!!

    private lateinit var viewModel: DasboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDasboardBinding.inflate(inflater,container,false)
        return binding.root
//        return inflater.inflate(R.layout.fragment_dasboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cardWorkers.setOnClickListener(){
            findNavController().navigate(DasboardFragmentDirections.actionDasboardNavToWorkerNav())
        }

        binding.card2.setOnClickListener(){
            findNavController().navigate(DasboardFragmentDirections.actionDasboardNavToLiveStockNav())
        }

        binding.cardPurchases.setOnClickListener(){
            findNavController().navigate(DasboardFragmentDirections.actionDasboardNavToPurchaseNav())
        }

        binding.cardSales.setOnClickListener(){
            findNavController().navigate(DasboardFragmentDirections.actionDasboardNavToSalesNav())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}