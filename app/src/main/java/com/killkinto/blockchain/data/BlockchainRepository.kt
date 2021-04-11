package com.killkinto.blockchain.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BlockchainRepository(
    private val localDataSource: BlockchainDataSource,
    private val remoteDataSource: BlockchainDataSource
) : IBlockchainRepository {
    override suspend fun getTransactionsPerSecond(forceUpdate: Boolean): List<Quote> {
        var quotes: List<Quote> = emptyList()

        if (forceUpdate) {
            val blockchain = remoteDataSource.getTransactionsPerSecond()
            blockchain?.let {
                withContext(Dispatchers.IO) {
                    localDataSource.save(blockchain.quotes)
                    quotes = it.quotes
                }
            }
        } else {
            withContext(Dispatchers.IO) {
                quotes = try {
                    localDataSource.getQuotes()
                } catch (cause: Throwable) {
                    throw  cause
                }
            }
        }
        return quotes
    }
}