package hemel.van.meincore.network.responses

import com.google.gson.annotations.SerializedName

data class NekosBestWaifu(
    @field:SerializedName("artist_href") val artistHref: String,
    @field:SerializedName("artist_name") val artistName: String,
    @field:SerializedName("source_url") val sourceUrl: String,
    @field:SerializedName("url") val url: String
)
