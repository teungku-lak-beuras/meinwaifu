package hemel.van.meinwaifu.remote.models

import com.google.gson.annotations.SerializedName

/*
 * JSON contract is:
 * {
 *   "results": [
 *     {
 *       "artist_href": "https://www.pixiv.net/en/users/5846268",
 *       "artist_name": "Goback",
 *       "source_url": "https://www.pixiv.net/en/artworks/96981529",
 *       "url": "https://nekos.best/api/v2/waifu/68f934c9-2aba-4170-841f-3381b1a3d10a.png"
 *     }
 *   ]
 * }
 */

data class NekosBestWaifuModel(
    @field:SerializedName("results") val results: List<Waifu>
)

data class Waifu(
    @field:SerializedName("artist_href") val artistHref: String,
    @field:SerializedName("artist_name") val artistName: String,
    @field:SerializedName("source_url") val sourceUrl: String,
    @field:SerializedName("url") val url: String
)
