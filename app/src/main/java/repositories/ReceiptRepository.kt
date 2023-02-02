package repositories

import model.Receipt
import model.ReceiptServiceResponse

interface ReceiptRepository {

    suspend fun getReceipts(): ReceiptServiceResponse
}