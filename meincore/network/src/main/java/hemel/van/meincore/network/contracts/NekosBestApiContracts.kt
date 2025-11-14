package hemel.van.meincore.network.contracts

import hemel.van.meincore.entitity.NekosBestWaifuEntity
import hemel.van.meincore.network.utilities.ApiState
import kotlinx.coroutines.flow.Flow

interface NekosBestApiContracts {
    suspend fun getWaifu(amount: Int = 15): Flow<ApiState<List<NekosBestWaifuEntity>>>
}
