package ug.ac.myivanfarm.ui

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import ug.ac.myivanfarm.data.retrofit.WorkerObj
import ug.ac.myivanfarm.data.retrofit.WorkerResponse
import ug.ac.myivanfarm.data.retrofit.WorkerRetrofitInstance
import ug.ac.myivanfarm.data.room.DBbuilder
import ug.ac.myivanfarm.data.room.WorkerModel

class WorkerViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    fun getAllDbWorkers(context: Context):Flow<List<WorkerModel>?> = flow {

        try{
            val db =  DBbuilder().initializeDB(context).createWorkerDao().selectAllWorkersModel()
            emit(db)
        } catch (t: Throwable){
            emit(null)
        }

//        val x  = {
//                    viewModelScope.async(Dispatchers.IO) {
//                        val retrofit =
//                            WorkerRetrofitInstance().createWorkerRetrofitInstance().getAllWorkers().response
//                        retrofit
//                    }
//                 }
//        return x.await()


    }

//    suspend fun getAllRemoteWorkers(): List<Worker>{
//        var worker: List<Worker> ?= null
//        val x = viewModelScope.async(Dispatchers.IO){
//            val worker = WorkerRetrofitInstance().createWorkerRetrofitInstance().getAllWorkers().response
//            worker
//
//        }
//        worker = x.await()
//        Log.d("WorkerVVM", "$worker")
//        return worker
//    }

    fun downloadRemoteWorkers(context: Context){
        var worker: List<WorkerObj> ?= null
        viewModelScope.launch(Dispatchers.IO){
            try{
                val worker = WorkerRetrofitInstance().createWorkerRetrofitInstance().getAllWorkers().response
                val x = convertToWorkerModel(worker)
                Log.d("Workers convert","$x")
               if( x != null){
                   insertWorkerModel(context, x )
               }
                //display in the workers
            }catch(e: Throwable){
                Log.d("Worker ViewModeal",": $e")
            }catch(e: Exception){
                Log.d("Worker ViewModeal",": $e")
            }

        }

    }

    private fun convertToWorkerModel(workerObjList: List<WorkerObj>): List<WorkerModel> {
        val workerModelList = mutableListOf<WorkerModel>()

        for (workerObj in workerObjList) {
            val workerModel = WorkerModel(

                fname = workerObj.f_name,
                lname = workerObj.l_name,
                title = workerObj.title,
                phone = workerObj.phone,
                password = workerObj.password,
                gender = workerObj.gender,
                image = workerObj.image,
                age = workerObj.age
            )
            workerModelList.add(workerModel)
        }

        return workerModelList
    }

//    insertWorkerModel(context: Context,item: WorkerModel)
    fun insertWorkerModel(context: Context,item: List<WorkerModel>){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val db = DBbuilder().initializeDB(context).createWorkerDao()

                //first deletes all the workers
                db.deleteWorkers()
                for ( i in item){
                    db.insertWorkersModel(i)
                    Log.d("workerView adding worker","$i")
                }


            }catch (e: Throwable){
//                Toast.makeText(this@WorkerViewModel,"$e", Toast.LENGTH_LONG).how()
                Log.d("WorkerViewModel","$e")
//                    e.printStackTrace()
            }

        }
    }
}