package com.killkinto.blockchain.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.killkinto.blockchain.data.Quote
import com.killkinto.blockchain.data.local.BlockchainContract

@Dao
interface BlockchainDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertQuotes(quotes: List<Quote>)

    @Query("SELECT * FROM ${BlockchainContract.BLOCKCHAIN_QUOTE_TABLE}")
    fun getQuotes() : List<Quote>
}