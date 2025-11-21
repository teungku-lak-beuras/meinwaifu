package hemel.van.meincore.network.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NekosBestApiSerializable(
    @SerialName("results") val results: List<NekosBestApiWaifu>
)
