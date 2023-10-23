package ug.ac.myivanfarm.data.retrofit

data class LiveStockResponse(
    var errorCode: Int?,
    var message: String?,
    var response: List<LiveStockObj>
)


