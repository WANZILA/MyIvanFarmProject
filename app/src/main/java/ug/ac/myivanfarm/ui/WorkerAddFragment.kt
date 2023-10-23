package ug.ac.myivanfarm.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import ug.ac.myivanfarm.R
import ug.ac.myivanfarm.data.retrofit.WorkerObj
import ug.ac.myivanfarm.data.retrofit.WorkerRetrofitInstance
import ug.ac.myivanfarm.databinding.FragmentWorkerAddBinding
import retrofit2.Response

class WorkerAddFragment : Fragment() {

    private var _binding : FragmentWorkerAddBinding ?= null
    val binding get() = _binding!!

//    private lateinit var viewModel: WorkerAddViewModel

    val viewModel: WorkerAddViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWorkerAddBinding.inflate(inflater,container, false)

        binding.elevatedButton.setOnClickListener(){
            CoroutineScope(Dispatchers.IO).launch {
                var fname = binding.txtFName.text
                var lname = binding.txtLName.text
                var title = binding.txtTitle.text
                var phone = binding.txtPhone.text
                var password = binding.txtPassword.text
                var gender = binding.txtGender.text
                var image = binding.image
                var age = binding.txtAge.text
//                val workerData = WorkerObj(
//                    f_name = "Jacob",
//                    l_name = "Jacob",
//                    title = "Worker",
//                    phone = "0704",
//                    password = "Jacob",
//                    gender = "male",
//                    image = "",
//                    age = "200"
//                )
//                val nameRequestBody = fname?.toRequestBody(MultipartBody.FORM)



                val fnameRequestBody = binding.txtFName.text
                    .toString()?.toRequestBody(MultipartBody.FORM)
                val lnameRequestBody = binding.txtLName.text
                    .toString()?.toRequestBody(MultipartBody.FORM)
                val titleRequestBody = binding.txtTitle.text
                    .toString()?.toRequestBody(MultipartBody.FORM)
                val phoneRequestBody = binding.txtPhone.text
                    .toString()?.toRequestBody(MultipartBody.FORM)
                val passwordRequestBody = binding.txtPassword.text
                    .toString()?.toRequestBody(MultipartBody.FORM)
                val genderRequestBody = binding.txtGender.text
                    .toString()?.toRequestBody(MultipartBody.FORM)
                val imageRequestBody = binding.image
                    .toString()?.toRequestBody(MultipartBody.FORM)
                val ageRequestBody = binding.txtAge.text
                    .toString()?.toRequestBody(MultipartBody.FORM)

                try {
                    val retrofit = WorkerRetrofitInstance().createWorkerRetrofitInstance().addWorker(fnameRequestBody,
                        lnameRequestBody,
                        titleRequestBody,
                        phoneRequestBody,
                        passwordRequestBody,
                        genderRequestBody,
                        imageRequestBody,
                        ageRequestBody
                    )

                    withContext(Dispatchers.Main) {
                        if (retrofit.isSuccessful) {
                            // Handle successful response
                            Toast.makeText(requireContext(), "Congs", Toast.LENGTH_LONG).show()
                            retrofit.body()
//                             fname = null
//                             lname = null
//                             title = null
//                             phone = null
//                             password = null
//                             gender = null
////                             image = ""
//                            age = null

                            fname?.clear()
                            lname?.clear()
                            title?.clear()
                            phone?.clear()
                            password?.clear()
                            gender?.clear()
//                            image?.
                            age?.clear()

                            findNavController().navigate(WorkerAddFragmentDirections.actionWorkerAddNavToWorkerNav())
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
//        return inflater.inflate(R.layout.fragment_worker_add, container, false)
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