package haui.do_an.apptest.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import haui.do_an.apptest.domain.models.Address
import haui.do_an.apptest.domain.usecase.SearchAddress
import haui.do_an.apptest.presentation.UiState
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewmodel @Inject constructor(
    val searchAddress: SearchAddress
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Initial)
    val uiState: StateFlow<UiState> = _uiState

    private val _address = MutableStateFlow<List<Address>>(emptyList())
    val address: StateFlow<List<Address>> = _address

    private val query = MutableStateFlow("")

    private var lastQuery: String = ""

    init {
        viewModelScope.launch(Dispatchers.IO) {
            query.debounce(200)
                .filter { it.isNotEmpty() && it != lastQuery }
                .distinctUntilChanged()
                .collect { q ->
                    _uiState.value = UiState.Loading
                    lastQuery = q
                    val result = searchAddress.searchAddress(q)
                    if (result.first == "Success"){
                        _address.value = result.second
                        _uiState.value = UiState.Initial
                    } else{
                        _uiState.value = UiState.Error(result.first)
                        Log.d("error", result.first)
                    }
                }
        }
    }

    fun search(keyword: String){
        query.value = keyword
    }

    fun resetUiState(){
        _uiState.value = UiState.Initial
    }

    fun resetSearchResult(){
        _address.value = emptyList()
    }
}