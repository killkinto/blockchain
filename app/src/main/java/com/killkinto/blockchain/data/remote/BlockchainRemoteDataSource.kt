package com.killkinto.blockchain.data.remote

import com.killkinto.blockchain.data.Blockchain
import com.killkinto.blockchain.data.BlockchainDataSource
import com.killkinto.blockchain.data.Quote

class BlockchainRemoteDataSource(private val api: BlockchainInfoApi) : BlockchainDataSource {
    override suspend fun getTransactionsPerSecond(): Blockchain {
        return api.retrofitService.getTransactionsPerSecond("5weeks", "8hours")
    }

    override suspend fun getQuotes(): List<Quote> {
        throw NotImplementedError()
    }

    override suspend fun save(quotes: List<Quote>) {
        throw NotImplementedError()
    }
}