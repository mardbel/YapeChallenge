package viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import model.Recipe
import repositories.ReceiptRepository
import javax.inject.Inject

@HiltViewModel
class ReceiptsViewModel @Inject constructor(private val recipeRepository: ReceiptRepository) : ViewModel() {

    private val _receiptsList = MutableLiveData<List<Recipe>>()
    val receiptsList: LiveData<List<Recipe>>
        get() = _receiptsList

    private var recipesList = listOf<Recipe>()

    private val _loadingState = MutableLiveData<Boolean>().apply {  postValue(true) }
    val loadingState: LiveData<Boolean> get() = _loadingState
    private val _state = MutableLiveData<State>()
    val state: LiveData<State>
        get() = _state

    fun getAllTheReceipts() {
        _loadingState.value = true
        viewModelScope.launch {
            val response = recipeRepository.getRecipes()
            if (response.success) {
                recipesList = response.recipe
                _state.value = State.Success(response.recipe)

                _loadingState.value = false
            } else _state.value = State.Failure(response.message)
        }
    }

    fun getReceiptById(id: String) : Recipe? {
        return recipesList.firstOrNull{
            it.id == id
        }
    }

    sealed class State {
        data class Success(val recipes: List<Recipe>) : State()
        data class Failure(val cause: String) : State()
    }
}