package com.killkinto.blockchain.overview

import android.app.Application
import androidx.lifecycle.*
import com.killkinto.blockchain.data.IBlockchainRepository
import com.killkinto.blockchain.data.Quote
import com.killkinto.blockchain.util.isInternetAvailable
import kotlinx.coroutines.launch

enum class Status { LOADING, ERROR, ERROR_CONNECT, EMPTY, DONE }

class OverviewViewModel(
    private val repository: IBlockchainRepository,
    private val app: Application
) : ViewModel() {
    private val _status = MutableLiveData<Status>()
    val status: LiveData<Status>
        get() = _status

    private val _quotes = MutableLiveData<List<Quote>>()
    val quotes: LiveData<List<Quote>>
        get() = _quotes

    val lastQuote = Transformations.map(_quotes) { values ->
        values.first()
    }

    val otherQuotes = Transformations.map(_quotes) { values ->
        values.subList(1, values.size)
    }

    init {
        loadDatas()
    }

    private fun loadDatas() {
        _status.value = Status.LOADING
        viewModelScope.launch {
            try {
                var values = repository.getTransactionsPerSecond()
                if (values.isEmpty()) {
                    if (isInternetAvailable(app.applicationContext)) {
                        values = repository.getTransactionsPerSecond(true)
                        if (values.isEmpty()) {
                            _status.value = Status.EMPTY
                            _quotes.value = emptyList()
                        }
                    } else {
                        _status.value = Status.ERROR_CONNECT
                        _quotes.value = emptyList()
                    }
                }

                if (values.isNotEmpty()) {
                    _quotes.value = values.sortedByDescending { it.timestamp }
                    _status.value = Status.DONE
                }
            } catch (e: Exception) {
                _status.value = Status.ERROR
            }
        }
    }
}

class OverViewViewModelFactory(
    private val repository: IBlockchainRepository,
    private val app: Application
) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return (OverviewViewModel(repository, app) as T)
    }
}