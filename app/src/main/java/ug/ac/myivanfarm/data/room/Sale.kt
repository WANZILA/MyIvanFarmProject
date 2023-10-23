package ug.ac.myivanfarm.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Sale(
    @PrimaryKey( autoGenerate = true) val id: Int = 1,
    @ColumnInfo(name="animal_id") val animalId: String?,
    var price: String?,
    @ColumnInfo(name="sold_by") var soldBy: String?,
    @ColumnInfo(name="sold_to") val soldTo: String?,
    val quantity: String?
)
