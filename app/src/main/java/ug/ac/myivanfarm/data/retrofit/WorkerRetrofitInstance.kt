package ug.ac.myivanfarm.data.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WorkerRetrofitInstance {
    //logging http data from the server
    private fun clientOKHTTP(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }
    fun createWorkerRetrofitInstance(): WorkerService{
        val okClient = clientOKHTTP()
        val retrofit = Retrofit.Builder().baseUrl("https://ivansfarm.000webhostapp.com/api/").client(okClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(WorkerService::class.java)
    }




}