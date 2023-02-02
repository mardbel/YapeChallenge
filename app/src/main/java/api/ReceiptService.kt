package api

import model.ReceiptServiceResponse
import retrofit2.http.GET

interface ReceiptService {

    @GET("receipts")
    suspend fun getReceipts(): ReceiptServiceResponse//List<Receipt>

}