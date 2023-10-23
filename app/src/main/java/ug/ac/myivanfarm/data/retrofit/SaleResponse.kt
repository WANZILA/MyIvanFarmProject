package ug.ac.myivanfarm.data.retrofit


data class SaleResponse(
    var errorCode: Int?,
    var message: String?,
    var response: List<SaleObj>
)


