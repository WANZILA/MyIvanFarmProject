package ug.ac.myivanfarm.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@Database([WorkerModel::class, SaleModel::class, PurchaseModel::class, LiveStockModel::class],version = 1)
@TypeConverters(Convertor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun createWorkerDao(): WorkerDao


//    abstract fun createPurchaseDa0():
}