package com.killkinto.blockchain

import android.content.Context
import com.killkinto.blockchain.data.BlockchainRepository
import com.killkinto.blockchain.data.IBlockchainRepository
import com.killkinto.blockchain.data.local.BlockchainLocalDataSource
import com.killkinto.blockchain.data.local.BlockchainDatabase
import com.killkinto.blockchain.data.remote.BlockchainInfoApi
import com.killkinto.blockchain.data.remote.BlockchainRemoteDataSource
import com.killkinto.blockchain.data.remote.retrofit

object ServiceLocator {
    private var blockchainRepository: IBlockchainRepository? = null

    fun provideBlockchainRepository(context: Context): IBlockchainRepository {
        return blockchainRepository ?: createBlockchainRepository(context)
    }

    private fun createBlockchainRepository(context: Context): IBlockchainRepository {
        return BlockchainRepository(
            BlockchainLocalDataSource(BlockchainDatabase.getDatabase(context).blockchainDao()),
            createBlockchainRemoteDataSource()
        ).also {
            this.blockchainRepository = it
        }
    }

    private fun createBlockchainRemoteDataSource() = BlockchainRemoteDataSource(
        BlockchainInfoApi(retrofit)
    )
}