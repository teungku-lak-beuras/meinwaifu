package hemel.van.meincore.local

import android.content.Context
import hemel.van.meincore.local.dao.WaifuRoomDao
import hemel.van.meincore.local.database.WaifuRoomDatabase
import hemel.van.meincore.local.entity.WaifuRoomEntity

class LocalDataSource(context: Context) {
    private val waifuRoomDao: WaifuRoomDao

    init {
        val database = WaifuRoomDatabase.getWaifuRoomDatabase(context = context)
        waifuRoomDao = database.getWaifuRoomDao()
    }

    suspend fun getWaifu() = waifuRoomDao.getWaifu()

    suspend fun insertWaifu(waifuRoomEntity: WaifuRoomEntity) {
        waifuRoomDao.insertWaifu(waifuRoomEntity = waifuRoomEntity)
    }
}
