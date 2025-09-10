package haui.do_an.apptest.domain.usecase

import android.content.Context
import android.content.Intent
import android.net.Uri
import haui.do_an.apptest.data.repositories.AddressRepositoryImpl
import haui.do_an.apptest.domain.models.Address
import haui.do_an.apptest.domain.repositories.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class SearchAddress @Inject constructor(
    private val addressRepository: AddressRepositoryImpl
) {

    suspend fun searchAddress(query: String): Pair<String, List<Address>> {
        when (val result = addressRepository.searchLocation(query)) {
            is Result.Success -> {
                return Pair("Success", result.data)
            }
            is Result.Error -> {
                return Pair(result.message, emptyList())
            }
        }
    }
}