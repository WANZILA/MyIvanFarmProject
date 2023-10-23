package ug.ac.myivanfarm.ui

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import ug.ac.myivanfarm.data.retrofit.PurchaseObj
import ug.ac.myivanfarm.data.retrofit.SaleObj
import ug.ac.myivanfarm.data.retrofit.WorkerObj
import ug.ac.myivanfarm.data.retrofit.WorkerRetrofitInstance
import ug.ac.myivanfarm.data.room.DBbuilder
import ug.ac.myivanfarm.data.room.PurchaseModel
import ug.ac.myivanfarm.data.room.SaleModel

class PurchaseViewModel : ViewModel() {
    fun getAllDbData(context: Context): Flow<List<PurchaseModel>?> = flow {

        try{
            val db =  DBbuilder().initializeDB(context).createWorkerDao().selectAllPurchasesModel()
            emit(db)
        } catch (t: Throwable){
            emit(null)
        }

    }


    fun downloadRemoteData(context: Context){
        var worker: List<PurchaseObj> ?= null
        viewModelScope.launch(Dispatchers.IO){
            try{
                val data = WorkerRetrofitInstance().createWorkerRetrofitInstance().getAllPurchases().response
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

    private fun convertToModel(dataObjList: List<PurchaseObj>): List<PurchaseModel> {
        val dataModelList = mutableListOf<PurchaseModel>()

        for (dataObj in dataObjList) {
            val model = PurchaseModel(
                product= dataObj.quantity,
                price= dataObj.quantity,
                description= dataObj.quantity,
                quantity = dataObj.quantity,
                purchased_from= dataObj.quantity

            )
            dataModelList.add(model)
        }

        return dataModelList
    }

    //    save in db
    fun insertDBModels(context: Context, item: List<PurchaseModel>){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val db = DBbuilder().initializeDB(context).createWorkerDao()

                //first deletes all the workers
                db.deletePurchases()
                for ( i in item){
                    db.insertPurchaseModel(i)
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