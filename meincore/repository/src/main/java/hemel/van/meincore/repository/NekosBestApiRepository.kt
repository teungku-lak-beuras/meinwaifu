package hemel.van.meincore.repository

import hemel.van.meincore.entitity.NekosBestWaifuEntity
import hemel.van.meincore.network.NekosBestApiDataSource
import hemel.van.meincore.network.utilities.ApiState
import kotlinx.coroutines.flow.Flow

class NekosBestApiRepository(
    private val nekosBestApiDataSource: NekosBestApiDataSource
) {
    suspend fun getWaifu(amount: Int): Flow<ApiState<List<NekosBestWaifuEntity>>> {
        return nekosBestApiDataSource.getWaifu(amount = amount)
    }
}
