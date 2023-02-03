package viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import getOrAwaitValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import model.Receipt
import model.ReceiptServiceResponse
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import repositories.ReceiptRepository

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class ReceiptsViewModelTest {


    @get: Rule
    var rule : InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    /*@Test
    fun `given getAllTheReceipts() then should receive a list from the repository` () {
        //Arrange
        val list = listOf<Receipt>(mock())
        val apiResponse = mock<ReceiptServiceResponse>{
            on { receipt } doReturn list
            on { success } doReturn true
        }
        val repository = mock<ReceiptRepository>{
            onBlocking { getReceipts() } doReturn apiResponse
        }
        var viewmodel = ReceiptsViewModel(repository)

        //Act
        viewmodel.getAllTheReceipts()
        val result = viewmodel.receiptsList.getOrAwaitValue()

        //Assert
        assertThat(result).isEqualTo(list)
    }*/

    @Test
    fun `given getAllTheReceipts() when is not success then should post state in failure` () {
        //Arrange
        val errorMessage = "error"
        val apiResponse = mock<ReceiptServiceResponse>{
            on { message } doReturn errorMessage
            on { success } doReturn false
        }
        val repository = mock<ReceiptRepository>{
            onBlocking { getReceipts() } doReturn apiResponse
        }
        var viewmodel = ReceiptsViewModel(repository)

        //Act
        viewmodel.getAllTheReceipts()
        val result = viewmodel.state.getOrAwaitValue()

        //Assert
        assertThat(result).isEqualTo(ReceiptsViewModel.State.Failure(errorMessage))
    }

    @Test
    fun `given getAllTheReceipts() when success then should post success state` () {
        //Arrange
        val list = listOf<Receipt>(mock())
        val apiResponse = mock<ReceiptServiceResponse>{
            on { receipt } doReturn list
            on { success } doReturn true
        }
        val repository = mock<ReceiptRepository>{
            onBlocking { getReceipts() } doReturn apiResponse
        }
        var viewmodel = ReceiptsViewModel(repository)

        //Act
        viewmodel.getAllTheReceipts()
        val result = viewmodel.state.getOrAwaitValue()

        //Assert
        assertThat(result).isEqualTo(ReceiptsViewModel.State.Success(list))
    }

    @Test
    fun `given getReceiptById() when it is found then it should return it` () {
        //Arrange
        val recipe = mock<Receipt> {
            on { id } doReturn "3"
        }
        val list = listOf<Receipt>(recipe)
        val apiResponse = mock<ReceiptServiceResponse>{
            on { receipt } doReturn list
            on { success } doReturn true
        }
        val repository = mock<ReceiptRepository>{
            onBlocking { getReceipts() } doReturn apiResponse
        }
        var viewmodel = ReceiptsViewModel(repository)

        //Act
        viewmodel.getAllTheReceipts()
        val result = viewmodel.getReceiptById("3")

        //Assert
        assertThat(result).isEqualTo(recipe)
    }

    @Test
    fun `given getReceiptById() when it not is found then it should return null` () {
        //Arrange

        val list = listOf<Receipt>()
        val apiResponse = mock<ReceiptServiceResponse>{
            on { receipt } doReturn list
            on { success } doReturn true
        }
        val repository = mock<ReceiptRepository>{
            onBlocking { getReceipts() } doReturn apiResponse
        }
        var viewmodel = ReceiptsViewModel(repository)

        //Act
        viewmodel.getAllTheReceipts()
        val result = viewmodel.getReceiptById("3")

        //Assert
        assertThat(result).isNull()
    }
}