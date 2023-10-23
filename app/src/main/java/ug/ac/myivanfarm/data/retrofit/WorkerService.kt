package ug.ac.myivanfarm.data.retrofit

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import ug.ac.myivanfarm.data.model.AuthResponse
import ug.ac.myivanfarm.data.model.AuthResponseData


interface WorkerService {
    @GET("get_users.php")
    suspend fun getAllWorkers():WorkerResponse

    @GET("get_animals.php")
    suspend fun getAllAminals():LiveStockResponse

    @GET("get_sales.php")
    suspend fun  getAllSales():SaleResponse

    @GET("get_purchases.php")
    suspend fun  getAllPurchases():PurchaseResponse

//    @FormUrlEncoded
//    @POST("user/edit")
//    fun updateUser(
//        @Field("f_name") first: String?,
//        @Field("f_name") last: String?
//    ): Call<WorkerObj?>?

//    @Multipart
//    suspend fun createUser(
//        @Part f_name:
//    )

//    @POST("insert_user.php")
//    @FormUrlEncoded
//    suspend  fun  addWorker(@FieldMap params: Map<String, String?>): Call<ResponseBody>

    @Multipart
    @POST("insert_user.php")
    suspend fun addWorker(
        @Part("f_name") fname: RequestBody?,
        @Part("l_name") sname: RequestBody?,
        @Part("title") title: RequestBody?,
        @Part("phone")phone: RequestBody?,
        @Part("password")password: RequestBody?,
        @Part("gender")gender: RequestBody?,
    @Part("image")image: RequestBody?,
    @Part("age")age: RequestBody?
    ): Response<ResponseBody>


    @Multipart
    @POST("sell_animal.php")
    suspend fun  addSale(
        @Part("animal_id") tagnum: RequestBody?,
        @Part("price") price: RequestBody?,
        @Part("sold_by") sold: RequestBody?,
        @Part("sold_to") buyer: RequestBody?,
        @Part("quantity") quantity: RequestBody?
    ): Response<ResponseBody>

    @Multipart
    @POST("buy_input.php")
    suspend fun  addPurchase(
        @Part("product") product: RequestBody?,
        @Part("price") price: RequestBody?,
        @Part("description") description: RequestBody?,
        @Part("quantity") quantity: RequestBody?,
        @Part("purchased_from") purchased_from: RequestBody?
    ): Response<ResponseBody>

    @Multipart
    @POST("insert_animal.php")
    suspend fun  addLiveStock(
        @Part("tag_number") product: RequestBody?,
        @Part("name") name: RequestBody?,
        @Part("type") type: RequestBody?,
        @Part("breed") breed: RequestBody?,
        @Part("image") purchased_from: RequestBody?,
        @Part("age") age: RequestBody?,
        @Part("gender") gender: RequestBody?,
        @Part("weight") weight: RequestBody?,
        @Part("color") color: RequestBody?,
        @Part("available") available: RequestBody?
    ): Response<ResponseBody>

//    @Field("phone") phone:String, @Field("password") password:String
    @FormUrlEncoded
    @POST("login.php")
    suspend fun loginUser(@Field("phone") phone:String, @Field("password") password:String): AuthResponseData



}