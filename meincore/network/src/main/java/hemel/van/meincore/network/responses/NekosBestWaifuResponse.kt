package hemel.van.meincore.network.responses

import com.google.gson.annotations.SerializedName

data class NekosBestWaifuResponse(
    @field:SerializedName("results") val results: List<NekosBestWaifu>
)
