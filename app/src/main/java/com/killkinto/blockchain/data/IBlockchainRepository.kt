package com.killkinto.blockchain.data

interface IBlockchainRepository {
    suspend fun getTransactionsPerSecond(forceUpdate: Boolean = false) : List<Quote>
}