package hemel.van.meincore.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import hemel.van.meincore.local.entity.WaifuRoomEntity

@Dao
interface WaifuRoomDao {
    @Query("SELECT * FROM waifu_room ORDER BY id DESC")
    suspend fun getWaifu(): List<WaifuRoomEntity>

    @Insert
    suspend fun insertWaifu(waifuRoomEntity: WaifuRoomEntity)
}
