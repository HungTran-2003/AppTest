package haui.do_an.apptest.data.api

import haui.do_an.apptest.data.models.Address
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationIQService {

    @GET("search")
    suspend fun searchLocation(
        @Query("q") query: String,
        @Query("format") format: String = "json",
        @Query("limit") limit: Int = 10,
        @Query("key") key: String,
        @Query("accept-language") acceptLanguage: String = "vi"
    ): Response<List<Address>>

}