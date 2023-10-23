package ug.ac.myivanfarm.ui

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ug.ac.myivanfarm.R
import ug.ac.myivanfarm.data.model.showError
import ug.ac.myivanfarm.data.retrofit.WorkerRetrofitInstance
import ug.ac.myivanfarm.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    var _binding: FragmentLoginBinding ?= null
    val binding get() = _binding!!

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater,container, false)
        return binding.root
//        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.elevatedButton.setOnClickListener(){

            with(binding){
                when{
                    txtUName.text!!.isEmpty() ->{
                        showDialog("username","field empty")
                    }
                    txtPassword.text!!.isEmpty() ->{
                        showDialog("password", "field empty")
                    }
                    else -> {
                        val phone = txtUName.text.toString()
                        val password = txtPassword.text.toString()
                        login(phone, password)
                    }
                }
            }


        }
    }

    fun login(phone: String, password: String){
        CoroutineScope(Dispatchers.IO).launch {
            try{
                val retrofit = WorkerRetrofitInstance().createWorkerRetrofitInstance().loginUser(phone, password)

                withContext(Dispatchers.Main){
                    if(retrofit.response.id == "1"){
                        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToDasboardNav())
                    } else{
                        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToUserDashboardFragment())
                    }
                }
            }catch (e: Throwable){
                withContext(Dispatchers.Main){
                    showDialog("error", showError(e))
                }
            }
        }
    }
    fun showDialog(title:String, message:String){
        val alert = AlertDialog.Builder(requireContext()).setTitle(title).setMessage(message)
            .setNegativeButton("Cancel") { p0, p1 -> p0?.dismiss()}

        alert.create()
        alert.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}