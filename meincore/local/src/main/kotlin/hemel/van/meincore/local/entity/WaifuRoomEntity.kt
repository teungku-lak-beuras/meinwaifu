package hemel.van.meincore.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "waifu_room")
data class WaifuRoomEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "artistHref") val artistHref: String,
    @ColumnInfo(name = "artistName") val artistName: String,
    @ColumnInfo(name = "sourceUrl") val sourceUrl: String,
    @ColumnInfo(name = "url") val url: String
)
