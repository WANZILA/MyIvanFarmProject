package ug.ac.myivanfarm.data.model

import com.google.gson.Gson
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import retrofit2.HttpException

fun showError(t: Throwable): String{
    val x = when(t){
        is SocketTimeoutException -> "Connection Time out"
        is ConnectException -> "No Internet Access"
        is UnknownHostException -> "Unable To connect to Server"
        is HttpException -> {
            val reader = t.response()?.errorBody()?.charStream()

            val error = Gson().fromJson(reader, ErrorResponse::class.java)
            error.message
        }
        else -> "An error occured"
    }
    return x ?: "Unkown Error"

}