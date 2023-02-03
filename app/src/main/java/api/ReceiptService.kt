package api

import model.RecipeServiceResponse
import retrofit2.http.GET

interface ReceiptService {

    @GET("receipts")
    suspend fun getReceipts(): RecipeServiceResponse

}