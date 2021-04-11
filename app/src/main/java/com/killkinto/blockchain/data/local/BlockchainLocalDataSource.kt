package com.killkinto.blockchain.data.local

import com.killkinto.blockchain.data.Blockchain
import com.killkinto.blockchain.data.BlockchainDataSource
import com.killkinto.blockchain.data.Quote
import com.killkinto.blockchain.data.local.dao.BlockchainDao

class BlockchainLocalDataSource(private val dao: BlockchainDao) : BlockchainDataSource {
    override suspend fun getTransactionsPerSecond(): Blockchain {
        throw NotImplementedError()
    }

    override suspend fun getQuotes(): List<Quote> {
        try {
            return dao.getQuotes()
        } catch (e: Exception) {
            e.message
        }
        return emptyList()
    }

    override suspend fun save(quotes: List<Quote>) {
        dao.insertQuotes(quotes)
    }
}

object BlockchainContract {
    const val BLOCKCHAIN_QUOTE_TABLE = "blockchain_quote"
}