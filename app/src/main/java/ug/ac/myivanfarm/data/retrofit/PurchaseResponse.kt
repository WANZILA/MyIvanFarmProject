package ug.ac.myivanfarm.data.retrofit

data class PurchaseResponse(
    var errorCode: Int?,
    var message: String?,
    var response: List<PurchaseObj>
)


