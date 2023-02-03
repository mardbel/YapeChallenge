package viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import model.Receipt
import repositories.ReceiptRepository
import javax.inject.Inject

@HiltViewModel
class ReceiptsViewModel @Inject constructor(private val receiptRepository: ReceiptRepository) : ViewModel() {

    private val _receiptsList = MutableLiveData<List<Receipt>>()
    val receiptsList: LiveData<List<Receipt>>
        get() = _receiptsList

    private var recipesList = listOf<Receipt>()

    private val _loadingState = MutableLiveData<Boolean>().apply {  postValue(true) }
    val loadingState: LiveData<Boolean> get() = _loadingState
    private val _state = MutableLiveData<State>()
    val state: LiveData<State>
        get() = _state

    fun getAllTheReceipts() {
        _loadingState.value = true
        viewModelScope.launch {
            val response = receiptRepository.getReceipts()
            if (response.success) {
                recipesList = response.receipt
                _state.value = State.Success(response.receipt)

                _loadingState.value = false
            } else _state.value = State.Failure(response.message)
        }
    }

    fun getReceiptById(id: String) : Receipt? {
        return recipesList.firstOrNull{
            it.id == id
        }
    }

    sealed class State {
        data class Success(val receipts: List<Receipt>) : State()
        data class Failure(val cause: String) : State()
    }
}