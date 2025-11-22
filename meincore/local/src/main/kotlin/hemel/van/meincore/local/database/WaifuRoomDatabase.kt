package hemel.van.meincore.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import hemel.van.meincore.local.dao.WaifuRoomDao
import hemel.van.meincore.local.entity.WaifuRoomEntity

@Database(entities = [WaifuRoomEntity::class], version = 1, exportSchema = false)
abstract class WaifuRoomDatabase : RoomDatabase() {
    abstract fun getWaifuRoomDao(): WaifuRoomDao

    companion object {
        @Volatile
        private var instance: WaifuRoomDatabase? = null

        @JvmStatic
        fun getWaifuRoomDatabase(context: Context): WaifuRoomDatabase {
            if (instance == null) {
                synchronized(this) {
                    if (instance == null) {
                        instance = Room.databaseBuilder(
                                context = context,
                                klass = WaifuRoomDatabase::class.java,
                                name = "waifu_room_database"
                            ).build()
                    }
                }
            }
            return instance as WaifuRoomDatabase
        }
    }
}
