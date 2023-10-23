package ug.ac.myivanfarm.data.retrofit

import androidx.room.Entity
import androidx.room.PrimaryKey


data class WorkerResponse(
    var errorCode: Int?,
    var message: String?,
    var response: List<WorkerObj>
)

//@Entity("worker")
//@PrimaryKey(autoGenerate = true) val id:Int,
//data class Worker(
//
//    var f_name:String?,
//    var l_name:String?,
//    var title:String?,
//    var phone:String?,
//    var password:String?,
//    var gender:String?,
//    var image:String?,
//    var age:String?
//)
