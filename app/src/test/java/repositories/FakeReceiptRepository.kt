package repositories

import model.Receipt
import model.ReceiptServiceResponse

class FakeReceiptRepository : ReceiptRepository {

    val receipt1 = Receipt("Lomito", "1", "any", "-31.4200833", "11", "image")
    val receipt2 = Receipt("comida2", "2", "any", "11", "11", "image")
    val receipt3 = Receipt("comida3", "3", "any", "11", "11", "image")

    private val receiptServiceResponse = ReceiptServiceResponse(
        success = true,
        receipt = provideReceiptsMock(),
        message = "no message"
    )

    override suspend fun getReceipts(): ReceiptServiceResponse {
        return receiptServiceResponse
    }

    private fun provideReceiptsMock(): List<Receipt> {
        return listOf(receipt1, receipt2, receipt3)
    }
}