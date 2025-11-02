package hemel.van.meinwaifu.remote.api_services

import hemel.van.meinwaifu.remote.models.NekosBestWaifuModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NekosBestApiService {
    @GET("waifu")
    fun getWaifu(
        @Query("amount") amount: Int = 1
    ): Call<NekosBestWaifuModel>
}
