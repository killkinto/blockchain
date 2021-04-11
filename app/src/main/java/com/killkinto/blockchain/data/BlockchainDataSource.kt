package com.killkinto.blockchain.data

interface BlockchainDataSource {
    suspend fun getTransactionsPerSecond(): Blockchain?

    suspend fun getQuotes(): List<Quote>

    suspend fun save(quotes: List<Quote>)
}