package hemel.van.meincore.network

import hemel.van.meincore.network.contracts.NekosBestApiContracts
import hemel.van.meincore.network.responses.NekosBestWaifuResponse
import hemel.van.meincore.network.service.NekosBestApiService

class NekosBestApiDataSource(
    private val nekosBestApiService: NekosBestApiService
) : NekosBestApiContracts {
    override suspend fun getWaifu(amount: Int): NekosBestWaifuResponse {
        return nekosBestApiService.getWaifu(amount = amount)
    }
}
