package viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import model.Receipt
import repositories.ReceiptsRepository
import javax.inject.Inject

@HiltViewModel
class ReceiptsViewModel @Inject constructor(private val receiptsRepository: ReceiptsRepository) : ViewModel() {

    fun getAllTheReceipts() {
        _state.value = State.Loading()
        viewModelScope.launch {
            val receipts = receiptsRepository.getReceipts()
            if (receipts == null) {
                throw ReceiptsNotFoundedException()
            } else _receiptsList.postValue(receipts)
        }
    }

    fun getReceiptById(id: String) {

    }

    private val _state = MutableLiveData<State>()
    val state: LiveData<State>
        get() = _state

    private val _receiptsList = MutableLiveData<List<Receipt>>()
    val receiptsList: LiveData<List<Receipt>>
        get() = _receiptsList



    sealed class State {
        class Success(val receipts: List<Receipt>) : State()
        class Failure(val cause: Throwable) : State()
        class Loading : State()
    }

    class ReceiptsNotFoundedException : Exception("Receipts' list not available")
}