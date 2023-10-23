package ug.ac.myivanfarm.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WorkerModel (
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name="f_name") val fname:String?,
    @ColumnInfo(name="l_name") var lname:String?,
    var title:String?,
    var phone:String?,
    var password:String?,
    var gender:String?,
    var image:String?,
    var age:String?
)


// @PrimaryKey(autoGenerate = true) var id:Int,
//    var worker : Worker