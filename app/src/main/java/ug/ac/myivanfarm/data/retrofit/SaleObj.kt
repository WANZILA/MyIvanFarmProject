package ug.ac.myivanfarm.data.retrofit

data class SaleObj (
        var animal_id: String?,
        var price: String?,
        var sold_by: String?,
        val sold_to: String?,
        val quantity: String?
    )
