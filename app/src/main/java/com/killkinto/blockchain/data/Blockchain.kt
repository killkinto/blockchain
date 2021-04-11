package com.killkinto.blockchain.data

import androidx.room.*
import com.killkinto.blockchain.data.local.BlockchainContract
import com.squareup.moshi.Json

data class Blockchain(
    val name: String,
    val unit: String,
    val period: String,
    val description: String,
    @Json(name = "values")
    var quotes: List<Quote>
)

@Entity(tableName = BlockchainContract.BLOCKCHAIN_QUOTE_TABLE)
data class Quote(
    @Json(name = "x")
    @PrimaryKey
    val timestamp: Long,
    @Json(name = "y")
    val value: Double
)
