package repositories

import model.ReceiptServiceResponse

interface ReceiptRepository {

    suspend fun getReceipts(): ReceiptServiceResponse
}