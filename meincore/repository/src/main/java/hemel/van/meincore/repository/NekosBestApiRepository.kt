package hemel.van.meincore.repository

import hemel.van.meincore.entitity.WaifuEntityV1
import hemel.van.meincore.network.NekosBestApiDataSource
import hemel.van.meincore.repository.utilities.ApiState
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.SerializationException

class NekosBestApiRepository {
    private val nekosBestApiDataSource = NekosBestApiDataSource()

    fun getWaifuV1(amount: Int): Flow<ApiState<List<WaifuEntityV1>>> = flow {
        emit(ApiState.Loading)

        try {
            val response = nekosBestApiDataSource.getWaifuV1(amount = amount)
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
        // 4xx
        catch (exception: ClientRequestException) {
            emit(ApiState.Error("BAD_CLI: ${exception.response.status}"))
        }
        // 5xx
        catch (exception: ServerResponseException) {
            emit(ApiState.Error("BAD_SER: ${exception.response.status}"))
        }
        // Serialisation
        catch (exception: SerializationException) {
            emit(ApiState.Error("BAD_SER: ${exception.cause?.message.toString()}"))
        }
        // IO
        catch (exception: IOException) {
            emit(ApiState.Error("BAD_IO: ${exception.cause?.message.toString()}"))
        }
        // Bruh
        catch (exception: Exception) {
            emit(ApiState.Error("BAD_BAD: ${exception.cause?.message.toString()}"))
        }
    }
}
