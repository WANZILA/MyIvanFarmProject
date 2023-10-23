package ug.ac.myivanfarm.ui

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import ug.ac.myivanfarm.data.retrofit.SaleObj
import ug.ac.myivanfarm.data.retrofit.WorkerObj
import ug.ac.myivanfarm.data.retrofit.WorkerRetrofitInstance
import ug.ac.myivanfarm.data.room.DBbuilder
import ug.ac.myivanfarm.data.room.SaleModel

class SalesViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    // TODO: Implement the ViewModel

    fun getAllDbData(context: Context): Flow<List<SaleModel>?> = flow {

        try{
            val db =  DBbuilder().initializeDB(context).createWorkerDao().selectAllSalesModel()
            emit(db)
        } catch (t: Throwable){
            emit(null)
        }

    }


    fun downloadRemoteData(context: Context){
        var worker: List<WorkerObj> ?= null
        viewModelScope.launch(Dispatchers.IO){
            try{
                val data = WorkerRetrofitInstance().createWorkerRetrofitInstance().getAllSales().response
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

    private fun convertToModel(dataObjList: List<SaleObj>): List<SaleModel> {
        val dataModelList = mutableListOf<SaleModel>()

        for (dataObj in dataObjList) {
            val model = SaleModel(
                animal_id = dataObj.animal_id,
                price = dataObj.price,
                sold_by = dataObj.sold_by,
                sold_to = dataObj.sold_to,
                quantity = dataObj.quantity,
            )
            dataModelList.add(model)
        }

        return dataModelList
    }

    //    save in db
    fun insertDBModels(context: Context, item: List<SaleModel>){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val db = DBbuilder().initializeDB(context).createWorkerDao()

                //first deletes all the workers
                db.deleteSales()
                for ( i in item){
                    db.insertSaleModel(i)
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