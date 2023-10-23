package ug.ac.myivanfarm.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class PurchaseModel(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    var product: String?,
    var price: String?,
    var description: String?,
    var quantity: String?,
    var purchased_from: String?
)
