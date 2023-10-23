package ug.ac.myivanfarm.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LiveStockModel(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    var tag_number: String?,
    var name: String?,
    var type: String?,
    var breed: String?,
    var image: String?,
    var age: String?,
    var gender: String?,
    var weight: String?,
    var color: String?,
    var available: String?
)