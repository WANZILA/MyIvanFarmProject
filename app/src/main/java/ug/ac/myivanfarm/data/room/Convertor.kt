package ug.ac.myivanfarm.data.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import ug.ac.myivanfarm.data.retrofit.WorkerResponse

import ug.ac.myivanfarm.data.retrofit.WorkerObj

class Convertor {

    @TypeConverter
    fun convertProductObjectToString(item:WorkerObj): String {
        val x =  Gson().toJson(item)
        return x
    }

    @TypeConverter
    fun convertStringToProductObject(item:String): WorkerObj {
        val y = Gson().fromJson(item, WorkerObj::class.java)
        return y
    }
}