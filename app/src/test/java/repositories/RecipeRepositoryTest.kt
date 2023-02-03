package repositories

import api.ReceiptService
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import model.RecipeServiceResponse
import org.junit.Test

class RecipeRepositoryTest {

    @ExperimentalCoroutinesApi
    @Test
     fun `when getReceipt() then should receive a list from the service` () = runTest{
        //Arrange
        val response = mock<RecipeServiceResponse>()
        val service = mock<ReceiptService>{
            onBlocking { getReceipts() } doReturn response
        }
        val repository = ReceiptRepositoryImp(service)

        //Act
        val result = repository.getRecipes()

        //Assert
        assertThat(result).isEqualTo(response)
     }
}