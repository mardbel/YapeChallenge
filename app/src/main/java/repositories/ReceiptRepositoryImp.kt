package repositories

import api.ReceiptService
import model.ReceiptServiceResponse
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReceiptRepositoryImp @Inject constructor(
    private val receiptService: ReceiptService
) : ReceiptRepository {

    override suspend fun getReceipts(): ReceiptServiceResponse = receiptService.getReceipts()
}
