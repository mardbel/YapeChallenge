package api

import model.Receipt
import retrofit2.Call
import retrofit2.http.GET

interface ReceiptService {

    @GET("receipt")
    suspend fun getReceipts(): Call<List<Receipt>>

}