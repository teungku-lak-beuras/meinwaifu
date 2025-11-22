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
        val localResponse = localRepository.getWaifu()
        val networkResponse = nekosBestApiRepository.getWaifuV1(amount = amount)

        networkResponse.collect { value ->
            when (value) {
                is ApiState.Loading -> {
                    emit(value)
                }
                is ApiState.Success -> {
                    for (i in value.data) {
                        localRepository.insertWaifu(i)
                    }
                }
                is ApiState.Error -> {
                    /**
                     * Leave this block empty. In case of application's fresh setup, this will leave
                     * the database empty, and the local repository will handle it which will be
                     * further emitted by the local collecter below.
                     *
                     * In case of app already has been used before (database not empty anymore),
                     * throwing error upon network error will defeat the purpose of `offline-first`.
                     */
                }
            }
        }
        localResponse.collect { value ->
            when (value) {
                is ApiState.Loading -> {} // Loading state already has been handled by the network.
                is ApiState.Success -> emit(value)
                is ApiState.Error -> emit(value)
            }
        }
    }.flowOn(Dispatchers.IO)
}
