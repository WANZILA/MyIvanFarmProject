package ug.ac.myivanfarm.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import ug.ac.myivanfarm.data.retrofit.WorkerRetrofitInstance

class WorkerAddViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    suspend fun saveWorker(fname:String, lname:String){
        viewModelScope.launch(Dispatchers.IO){
//            val retrofit = WorkerRetrofitInstance().createWorkerRetrofitInstance().updateUser(fname,lname)
        }
    }
}