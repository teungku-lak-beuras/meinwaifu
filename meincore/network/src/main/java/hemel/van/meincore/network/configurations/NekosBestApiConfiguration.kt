package hemel.van.meincore.network.configurations

import hemel.van.meincore.network.BuildConfig
import hemel.van.meincore.network.service.NekosBestApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NekosBestApiConfiguration {
    companion object {
        fun getApiService(): NekosBestApiService {
            val loggingInterceptor = when(BuildConfig.DEBUG) {
                true -> HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                false -> HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.NEKOS_BEST_API)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit.create(NekosBestApiService::class.java)
        }
    }
}
