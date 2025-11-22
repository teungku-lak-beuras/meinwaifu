package hemel.van.meincore.network.configurations

import hemel.van.meincore.network.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.cio.CIO
import io.ktor.client.engine.cio.CIOEngineConfig
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

private const val timeout: Long = 30_000

fun provideEngine(): HttpClientEngineFactory<CIOEngineConfig> = CIO

fun createHttpClient(
    baseUrl: String = BuildConfig.NEKOS_BEST_API,
    enableLogging: Boolean = true
): HttpClient {
    return HttpClient(provideEngine()) {
        defaultRequest {
            url(baseUrl)
        }

        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true // Just in case of sudden API changes.
                }
            )
        }

        install(HttpTimeout) {
            connectTimeoutMillis = timeout
            requestTimeoutMillis = timeout
            socketTimeoutMillis = timeout
        }

        if (enableLogging) {
            install(Logging) {
                logger = Logger.ANDROID
                level = LogLevel.BODY
            }
        }
    }
}
