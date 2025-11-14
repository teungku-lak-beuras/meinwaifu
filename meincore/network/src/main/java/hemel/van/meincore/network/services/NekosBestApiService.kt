package hemel.van.meincore.network.service

import hemel.van.meincore.network.responses.NekosBestWaifuResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NekosBestApiService {
    @GET("waifu") suspend fun getWaifu(
        @Query("amount") amount: Int = 15
    ): NekosBestWaifuResponse
}
