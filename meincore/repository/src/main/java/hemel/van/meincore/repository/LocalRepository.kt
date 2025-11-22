package hemel.van.meincore.repository

import android.content.Context
import hemel.van.meincore.entitity.WaifuEntityV1
import hemel.van.meincore.local.LocalDataSource
import hemel.van.meincore.local.entity.WaifuRoomEntity
import hemel.van.meincore.repository.utilities.ApiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LocalRepository(
    private val context: Context
) {
    private val localDataSource = LocalDataSource(context = context)

    fun getWaifu(): Flow<ApiState<List<WaifuEntityV1>>> = flow {
        emit(ApiState.Loading)

        try {
            val response = localDataSource.getWaifu()

            if (response.isEmpty()) {
                throw Exception("Database is empty.")
            }

            val waifus = mutableListOf<WaifuEntityV1>()

            for (i in response) {
                waifus.add(
                    WaifuEntityV1(
                        artistHref = i.artistHref,
                        artistName = i.artistName,
                        sourceUrl = i.sourceUrl,
                        url = i.url
                    )
                )
            }
            emit(ApiState.Success(waifus))
        }
        catch (exception: Exception) {
            emit(ApiState.Error("BAD_DB: ${exception.message.toString()}"))
        }
    }

    suspend fun insertWaifu(waifu: WaifuEntityV1) {
        localDataSource.insertWaifu(
            WaifuRoomEntity(
                artistHref = waifu.artistHref,
                artistName = waifu.artistName,
                sourceUrl = waifu.sourceUrl,
                url = waifu.url
            )
        )
    }
}
