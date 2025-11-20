package hemel.van.meincore.repository

import hemel.van.meincore.entitity.WaifuEntityV1
import hemel.van.meincore.network.NekosBestApiDataSource
import hemel.van.meincore.network.configurations.NekosBestApiConfiguration
import hemel.van.meincore.repository.utilities.ApiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException

class NekosBestApiRepository(
//    private val nekosBestApiDataSource: NekosBestApiDataSource
) {
    private val nekosBestApiDataSource: NekosBestApiDataSource

    init {
        nekosBestApiDataSource = NekosBestApiDataSource(NekosBestApiConfiguration.getApiService())
    }

    fun getWaifu(amount: Int): Flow<ApiState<List<WaifuEntityV1>>> = flow {
        emit(ApiState.Loading)

        try {
            val response = nekosBestApiDataSource.getWaifu(amount = amount)
            val waifus = mutableListOf<WaifuEntityV1>()

            for (i in response.results) {
                waifus.add(
                    WaifuEntityV1(
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
