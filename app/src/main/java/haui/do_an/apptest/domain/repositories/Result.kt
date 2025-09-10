package haui.do_an.apptest.domain.repositories

import haui.do_an.apptest.domain.models.Address

sealed interface Result {

    data class Success(val data: List<Address>) : Result

    data class Error(val message: String) : Result

}