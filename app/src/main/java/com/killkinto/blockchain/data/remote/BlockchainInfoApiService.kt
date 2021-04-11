package com.killkinto.blockchain.data.remote

import com.killkinto.blockchain.data.Blockchain
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.blockchain.info/charts/"
private const val TIMESPAN = "timespan"
private const val ROLLING_AVERAGE = "rollingAverage"
private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

val retrofit: Retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

class BlockchainInfoApi(private val retrofit: Retrofit) {
    val retrofitService: BlockchainInfoService by lazy {
        retrofit.create(BlockchainInfoService::class.java)
    }
}

interface BlockchainInfoService {
    @GET("transactions-per-second")
    suspend fun getTransactionsPerSecond(
        @Query(TIMESPAN) timespan: String,
        @Query(ROLLING_AVERAGE) rollingAverage: String
    ): Blockchain
}