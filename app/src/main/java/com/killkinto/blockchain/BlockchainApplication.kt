package com.killkinto.blockchain

import android.app.Application
import com.killkinto.blockchain.data.IBlockchainRepository

class BlockchainApplication : Application() {
    val blockchainRepository: IBlockchainRepository
        get() = ServiceLocator.provideBlockchainRepository(this.applicationContext)
}