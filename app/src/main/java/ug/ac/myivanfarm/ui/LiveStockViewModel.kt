package ug.ac.myivanfarm.ui

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import ug.ac.myivanfarm.data.retrofit.LiveStockObj
import ug.ac.myivanfarm.data.retrofit.SaleObj
import ug.ac.myivanfarm.data.retrofit.WorkerObj
import ug.ac.myivanfarm.data.retrofit.WorkerRetrofitInstance
import ug.ac.myivanfarm.data.room.DBbuilder
import ug.ac.myivanfarm.data.room.LiveStockModel


class LiveStockViewModel : ViewModel() {
    fun getAllDbData(context: Context): Flow<List<LiveStockModel>?> = flow {

        try{
            val db =  DBbuilder().initializeDB(context).createWorkerDao().selectAllLiveStocksModel()
            emit(db)
        } catch (t: Throwable){
            emit(null)
        }

    }


    fun downloadRemoteData(context: Context){
        var worker: List<WorkerObj> ?= null
        viewModelScope.launch(Dispatchers.IO){
            try{
                val data = WorkerRetrofitInstance().createWorkerRetrofitInstance().getAllAminals().response
                val x = convertToModel(data)
                Log.d("Model convert","$x")
                if( x != null){
                    insertDBModels(context, x )
                }
                //display in the workers
            }catch(e: Throwable){
                Log.d("Model ViewModel",": $e")
            }catch(e: Exception){
                Log.d("Model ViewModel",": $e")
            }

        }

    }

    private fun convertToModel(dataObjList: List<LiveStockObj>): List<LiveStockModel> {
        val dataModelList = mutableListOf<LiveStockModel>()

        for (dataObj in dataObjList) {
            val model = LiveStockModel(
                        tag_number = dataObj.tag_number,
                        name = dataObj.name,
                        type = dataObj.type,
                        breed = dataObj.breed,
                        image = dataObj.image,
                        age = dataObj.age,
                        gender = dataObj.gender,
                        weight = dataObj.weight,
                        color = dataObj.color,
                        available = dataObj.available
            )
            dataModelList.add(model)
        }

        return dataModelList
    }

    //    save in db
    fun insertDBModels(context: Context, item: List<LiveStockModel>){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val db = DBbuilder().initializeDB(context).createWorkerDao()

                //first deletes all the workers
                db.deleteSales()
                for ( i in item){
                    db.insertLiveStocksModel(i)
                    Log.d("modelView adding model","$i")
                }


            }catch (e: Throwable){
//                Toast.makeText(this@WorkerViewModel,"$e", Toast.LENGTH_LONG).how()
                Log.d("ViewModel","$e")
//                    e.printStackTrace()
            }

        }
    }
}