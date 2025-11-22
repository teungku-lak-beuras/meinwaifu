package hemel.van.meincore.repository

import android.content.Context
import hemel.van.meincore.entitity.WaifuEntityV1
import hemel.van.meincore.repository.utilities.ApiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MeinWaifuRepository(
    private val context: Context
) {
    private val localRepository: LocalRepository = LocalRepository(context = context)
    private val nekosBestApiRepository: NekosBestApiRepository = NekosBestApiRepository()

    fun getWaifuV1(amount: Int): Flow<ApiState<List<WaifuEntityV1>>> = flow {
        val networkResponse = nekosBestApiRepository.getWaifuV1(amount = amount)
        val localResponse = localRepository.getWaifu()

        networkResponse.collect { value ->
            when (value) {
                is ApiState.Loading -> emit(value)
                is ApiState.Success -> {
                    for (i in value.data) {
                        localRepository.insertWaifu(i)
                    }
                }
                is ApiState.Error -> emit(value)
            }
        }
        localResponse.collect { value ->
            emit(value)
        }
    }.flowOn(Dispatchers.IO)
}
