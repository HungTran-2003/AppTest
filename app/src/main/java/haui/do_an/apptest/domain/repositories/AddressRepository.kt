package haui.do_an.apptest.domain.repositories

import haui.do_an.apptest.domain.repositories.Result
import haui.do_an.apptest.domain.models.Address

interface AddressRepository {
    suspend fun searchLocation(query: String): Result
}