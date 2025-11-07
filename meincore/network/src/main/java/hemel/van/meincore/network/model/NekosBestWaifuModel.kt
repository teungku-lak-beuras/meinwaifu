package hemel.van.meinwaifu.remote.models

import com.google.gson.annotations.SerializedName

data class NekosBestWaifuModel(
    @field:SerializedName("results") val results: List<Waifu>
)

data class Waifu(
    @field:SerializedName("artist_href") val artistHref: String,
    @field:SerializedName("artist_name") val artistName: String,
    @field:SerializedName("source_url") val sourceUrl: String,
    @field:SerializedName("url") val url: String
)
