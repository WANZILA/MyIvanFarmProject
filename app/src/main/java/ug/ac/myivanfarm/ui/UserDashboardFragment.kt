package ug.ac.myivanfarm.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import ug.ac.myivanfarm.R
import ug.ac.myivanfarm.databinding.FragmentUserDashboardBinding

class UserDashboardFragment : Fragment() {
    // TODO: Rename and change types of parameters

    var _binding: FragmentUserDashboardBinding ?= null
    val binding get() =  _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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


}