package ug.ac.myivanfarm.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import ug.ac.myivanfarm.data.retrofit.WorkerResponse
//import ug.ac.myivanfarm.data.retrofit.Worker

@Dao
interface  WorkerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWorkersModel(worker:WorkerModel)

    @Query("Delete FROM WorkerModel ")
    fun  deleteWorkers()

    @Query("SELECT * FROM workerModel ")
    fun  selectAllWorkersModel():List<WorkerModel>

//    sale
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSaleModel(sale:SaleModel)

    @Query("Delete FROM SaleModel ")
    fun  deleteSales()

    @Query("SELECT * FROM SaleModel ")
    fun  selectAllSalesModel():List<SaleModel>


// Purchases
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPurchaseModel(sale:PurchaseModel)

    @Query("Delete FROM PurchaseModel ")
    fun  deletePurchases()

    @Query("SELECT * FROM PurchaseModel ")
    fun  selectAllPurchasesModel():List<PurchaseModel>


// LiveStock

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLiveStocksModel(worker:LiveStockModel)

    @Query("Delete FROM LiveStockModel ")
    fun  deleteLiveStocks()

    @Query("SELECT * FROM LiveStockModel ")
    fun  selectAllLiveStocksModel():List<LiveStockModel>

}