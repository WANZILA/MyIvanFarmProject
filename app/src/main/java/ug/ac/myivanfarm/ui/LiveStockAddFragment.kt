package ug.ac.myivanfarm.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.http.Part
import ug.ac.myivanfarm.R
import ug.ac.myivanfarm.data.retrofit.WorkerRetrofitInstance
import ug.ac.myivanfarm.databinding.FragmentLiveStockAddBinding

class LiveStockAddFragment : Fragment() {

    private var _binding: FragmentLiveStockAddBinding? = null
    val binding get() = _binding!!

    private lateinit var viewModel: LiveStockAddViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLiveStockAddBinding.inflate(inflater, container, false)
        return binding.root
//        return inflater.inflate(R.layout.fragment_live_stock_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.elevatedButton.setOnClickListener() {
            CoroutineScope(Dispatchers.IO).launch {
                var tag = binding.txtTagNo.text
                var name = binding.txtName.text
                var bread = binding.txtBreed.text
                var gender = binding.txtGender.text
                var age = binding.txtAge.text
                var weight = binding.txtWeight.text
                var type = binding.txtType.text
                var color = binding.txtColor.text
                var avail = binding.txtAvailable.text
                var image = binding.image


                val tagRequestBody = tag.toString()?.toRequestBody(MultipartBody.FORM)
                val namequestBody = name.toString()?.toRequestBody(MultipartBody.FORM)
                val breadRequestBody = bread.toString()?.toRequestBody(MultipartBody.FORM)
                val imageRequestBody = image.toString()?.toRequestBody(MultipartBody.FORM)
                val genderRequestBody = gender.toString()?.toRequestBody(MultipartBody.FORM)
                val ageRequestBody = age.toString()?.toRequestBody(MultipartBody.FORM)
                val weightquestBody = weight.toString()?.toRequestBody(MultipartBody.FORM)
                val typeRequestBody = type.toString()?.toRequestBody(MultipartBody.FORM)
                val colorRequestBody = color.toString()?.toRequestBody(MultipartBody.FORM)
                val availRequestBody = avail.toString()?.toRequestBody(MultipartBody.FORM)

                try {
                    val retrofit =
                        WorkerRetrofitInstance().createWorkerRetrofitInstance().addLiveStock(
                            tagRequestBody,
                            namequestBody,
                            typeRequestBody,
                            breadRequestBody,
                            imageRequestBody,
                            ageRequestBody,
                            genderRequestBody,
                            weightquestBody,
                            colorRequestBody,
                            availRequestBody
                        )



                    withContext(Dispatchers.Main) {
                        if (retrofit.isSuccessful) {
                            // Handle successful response
                            Toast.makeText(requireContext(), "Congs", Toast.LENGTH_LONG).show()
                            retrofit.body()
//                            name?.clear()
                            tag?.clear()
                            name?.clear()
                            bread?.clear()
                            gender?.clear()
                            age?.clear()
                            weight?.clear()
                            type?.clear()
                            color?.clear()
                            avail?.clear()



                            findNavController().navigate(LiveStockFragmentDirections.actionLiveStockNavToLiveStockAddFragment())
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

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}