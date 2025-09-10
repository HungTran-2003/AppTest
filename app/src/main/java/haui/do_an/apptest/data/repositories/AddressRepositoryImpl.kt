package haui.do_an.apptest.data.repositories

import com.google.gson.internal.GsonBuildConfig
import haui.do_an.apptest.BuildConfig
import haui.do_an.apptest.domain.repositories.Result
import haui.do_an.apptest.data.api.LocationIQService
import haui.do_an.apptest.data.mapper.toDomainModel
import haui.do_an.apptest.domain.models.Address
import haui.do_an.apptest.domain.repositories.AddressRepository
import javax.inject.Inject

class AddressRepositoryImpl @Inject constructor(
    val locationIQService: LocationIQService

) : AddressRepository {

    private val API_KEY: String = BuildConfig.API_KEY
    override suspend fun searchLocation(query: String): Result {
        return try {
            val response = locationIQService.searchLocation(query, key = API_KEY)
            if (response.isSuccessful) {
                val addresses = response.body()?.map { it.toDomainModel() } ?: emptyList()
                Result.Success(addresses)
            } else {
                Result.Error("Error: ${response.code()}")
            }
        } catch (e: Exception) {
            Result.Error("Error: ${e.message}")
        }
    }
}