package hemel.van.meincore.network

import hemel.van.meincore.entitity.NekosBestWaifuEntity
import hemel.van.meincore.network.contracts.NekosBestApiContracts
import hemel.van.meincore.network.service.NekosBestApiService
import hemel.van.meincore.network.utilities.ApiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException

class NekosBestApiDataSource(
    private val nekosBestApiService: NekosBestApiService
) : NekosBestApiContracts {
    override suspend fun getWaifu(amount: Int): Flow<ApiState<List<NekosBestWaifuEntity>>> = flow {
        emit(ApiState.Loading)

        try {
            val response = nekosBestApiService.getWaifu(amount)
            val waifus = mutableListOf<NekosBestWaifuEntity>()

            for (i in response.results) {
                waifus.add(
                    NekosBestWaifuEntity(
                        artistHref = i.artistHref,
                        artistName = i.artistName,
                        sourceUrl = i.sourceUrl,
                        url = i.url
                    )
                )
            }
            emit(ApiState.Success(data = waifus))
        }
        // Response is successfully received but the response code is not 200-ish.
        catch (exception: HttpException) {
            emit(ApiState.Error("Error: ${exception.response()?.errorBody()?.string()}"))
        }
        // Response cannot be received at all.
        catch (exception: Exception) {
            emit(ApiState.Error("Error: ${exception.message.toString()}"))
        }
    }.flowOn(Dispatchers.IO)
}
