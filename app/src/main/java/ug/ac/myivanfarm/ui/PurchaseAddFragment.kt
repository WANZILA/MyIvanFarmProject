package ug.ac.myivanfarm.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import ug.ac.myivanfarm.R
import ug.ac.myivanfarm.data.retrofit.WorkerRetrofitInstance
import ug.ac.myivanfarm.databinding.FragmentPurchaseAddBinding

class PurchaseAddFragment : Fragment() {

    private var _binding : FragmentPurchaseAddBinding?= null
    val binding get() = _binding!!

     val viewModel: PurchaseAddViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPurchaseAddBinding.inflate(inflater,container, false)

        binding.elevatedButton.setOnClickListener(){
            CoroutineScope(Dispatchers.IO).launch {
                var item = binding.txtItem.text
                var qty = binding.txtQuantity.text
                var price = binding.txtPrice.text
                var desc  = binding.txtDescript.text
                var by  = binding.txtPurchaser.text



                val itemRequestBody = item.toString()?.toRequestBody(MultipartBody.FORM)
                val qtyRequestBody = qty.toString()?.toRequestBody(MultipartBody.FORM)
                val priceRequestBody = price.toString()?.toRequestBody(MultipartBody.FORM)
                val descRequestBody = desc.toString()?.toRequestBody(MultipartBody.FORM)
                val byRequestBody = by.toString()?.toRequestBody(MultipartBody.FORM)

                try {
                    val retrofit = WorkerRetrofitInstance().createWorkerRetrofitInstance().addPurchase(
                        itemRequestBody,
                        priceRequestBody,
                        descRequestBody,
                        qtyRequestBody,
                        byRequestBody
                    )

                    withContext(Dispatchers.Main) {
                        if (retrofit.isSuccessful) {
                            // Handle successful response
                            Toast.makeText(requireContext(), "Congs", Toast.LENGTH_LONG).show()
                            retrofit.body()
//                            name?.clear()
                            item?.clear()
                            qty?.clear()
                            price?.clear()
                            desc?.clear()
                            by?.clear()

                            findNavController().navigate(PurchaseFragmentDirections.actionPurchaseNavToPurchaseAddFragment())
                        } else {
                            // Handle unsuccessful response
                            Toast.makeText(requireContext(), "Failed", Toast.LENGTH_LONG).show()
                        }
                    }
                } catch (e: Throwable) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(requireContext(), "Throwable $e", Toast.LENGTH_LONG).show()
//                        e.printStackTrace()
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(requireContext(), "Exception $e", Toast.LENGTH_LONG).show()
                        e.printStackTrace()
                    }
                }
            }

        }

        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}