package viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import model.Receipt
import repositories.ReceiptRepositoryImp
import javax.inject.Inject

@HiltViewModel
class ReceiptsViewModel @Inject constructor(private val receiptRepositoryImp: ReceiptRepositoryImp) : ViewModel() {

    private val _receiptsList = MutableLiveData<List<Receipt>>()
    val receiptsList: LiveData<List<Receipt>>
        get() = _receiptsList

    private val _loadingState = MutableLiveData<Boolean>().apply {  postValue(true) }
    val loadingState: LiveData<Boolean> get() = _loadingState
    private val _state = MutableLiveData<State>()
    val state: LiveData<State>
        get() = _state

    fun getAllTheReceipts() {
        _loadingState.value = true
        viewModelScope.launch {
            delay(2000)
            val response = receiptRepositoryImp.getReceipts()
            if (response.success) {
                _state.value = State.Success(response.receipt)
                _receiptsList.value = response.receipt
                _loadingState.value = false
            } else _state.value = State.Failure(Throwable(response.message))
        }
    }

    fun getReceiptById(id: String) : Receipt? {
        _receiptsList.value?.forEach {
            if (it.id == id) return it
        }
        return null
    }

    sealed class State {
        class Success(val receipts: List<Receipt>) : State()
        class Failure(val cause: Throwable) : State()
    }
}