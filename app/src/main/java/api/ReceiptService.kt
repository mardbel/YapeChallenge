package api

import model.Receipt
import retrofit2.http.GET

interface ReceiptService {

    @GET("receipts")
    suspend fun getReceipts(): List<Receipt>

}