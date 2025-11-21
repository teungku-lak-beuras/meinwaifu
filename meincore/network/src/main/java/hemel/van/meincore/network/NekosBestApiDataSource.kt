package hemel.van.meincore.network

import hemel.van.meincore.network.configurations.createHttpClient
import hemel.van.meincore.network.responses.NekosBestApiSerializable
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class NekosBestApiDataSource {
    private val nekosBestApiClient: HttpClient = createHttpClient()

    suspend fun getWaifuV1(amount: Int): NekosBestApiSerializable {
        return nekosBestApiClient.get(
            urlString = "waifu",
            block = {
                url {
                    parameters.append("amount", amount.toString())
                }
            }
        ).body()
    }
}
