package hemel.van.meincore.network.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NekosBestApiWaifu(
    @SerialName("artist_href") val artistHref: String,
    @SerialName("artist_name") val artistName: String,
    @SerialName("source_url") val sourceUrl: String,
    @SerialName("url") val url: String
)
