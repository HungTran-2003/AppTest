package haui.do_an.apptest.presentation

sealed interface UiState {

    object Initial : UiState

    object Loading : UiState

    data class Error(val message: String) : UiState


}