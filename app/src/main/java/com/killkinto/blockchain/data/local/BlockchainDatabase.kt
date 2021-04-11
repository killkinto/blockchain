package com.killkinto.blockchain.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.killkinto.blockchain.data.Quote
import com.killkinto.blockchain.data.local.dao.BlockchainDao

@Database(entities = [Quote::class], version = 1)
abstract class BlockchainDatabase : RoomDatabase() {
    abstract fun blockchainDao() : BlockchainDao

    companion object {
        @Volatile
        private var INSTANCE: BlockchainDatabase? = null

        fun getDatabase(context: Context): BlockchainDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BlockchainDatabase::class.java,
                    "blockchain_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}