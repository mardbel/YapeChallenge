package repositories

import api.ReceiptService
import model.Receipt
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReceiptsRepository @Inject constructor(
    private val receiptService : ReceiptService
) {

    suspend fun getReceipts(): List<Receipt> {
        return receiptService.getReceipts()
    }
}