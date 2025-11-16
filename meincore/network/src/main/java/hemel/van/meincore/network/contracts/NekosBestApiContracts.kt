package hemel.van.meincore.network.contracts

import hemel.van.meincore.network.responses.NekosBestWaifuResponse

interface NekosBestApiContracts {
    suspend fun getWaifu(amount: Int = 15): NekosBestWaifuResponse
}
