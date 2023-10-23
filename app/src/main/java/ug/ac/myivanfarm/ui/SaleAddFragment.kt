package ug.ac.myivanfarm.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import ug.ac.myivanfarm.R
import ug.ac.myivanfarm.data.retrofit.WorkerRetrofitInstance
import ug.ac.myivanfarm.databinding.FragmentSaleAddBinding

class SaleAddFragment : Fragment() {

    private var _binding: FragmentSaleAddBinding ?= null
    val binding get() = _binding!!

    private lateinit var viewModel: SaleAddViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return inflater.inflate(R.layout.fragment_sale_add, container, false)
        _binding = FragmentSaleAddBinding.inflate(inflater, container, false)

        binding.elevatedButton.setOnClickListener(){
            CoroutineScope(Dispatchers.IO).launch {
                var tag = binding.txtTagNo.text
//                var name = binding.txtName.text
                var quantity = binding.txtQuantity.text
                var price = binding.txtPrice.text
                var seller = binding.txtSoldBy.text
                var buyer = binding.txtSoldTo.text


                val tagRequestBody = tag.toString()?.toRequestBody(MultipartBody.FORM)
//                val nameRequestBody = name.toString()?.toRequestBody(MultipartBody.FORM)
                val quantityRequestBody = quantity.toString()?.toRequestBody(MultipartBody.FORM)
                val priceRequestBody = price.toString()?.toRequestBody(MultipartBody.FORM)
                val  sellerRequestBody =  seller.toString()?.toRequestBody(MultipartBody.FORM)
                val buyerRequestBody = buyer.toString()?.toRequestBody(MultipartBody.FORM)

                try {
                    val retrofit = WorkerRetrofitInstance().createWorkerRetrofitInstance().addSale(
                        tagRequestBody,
                        priceRequestBody,
                        sellerRequestBody,
                        buyerRequestBody,
                        quantityRequestBody
                    )

                    withContext(Dispatchers.Main) {
                        if (retrofit.isSuccessful) {
                            // Handle successful response
                            Toast.makeText(requireContext(), "Congs", Toast.LENGTH_LONG).show()
                            retrofit.body()
//                            name?.clear()
                            tag?.clear()
                            quantity?.clear()
                            price?.clear()
                            seller?.clear()
//                            image?.
                            buyer?.clear()

                            findNavController().navigate(SaleAddFragmentDirections.actionSaleAddFragmentToSalesNav())
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