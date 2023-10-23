package ug.ac.myivanfarm.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SaleModel (
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    var animal_id: String?,
    var price: String?,
    var sold_by: String?,
    var sold_to: String?,
    var quantity: String?
)