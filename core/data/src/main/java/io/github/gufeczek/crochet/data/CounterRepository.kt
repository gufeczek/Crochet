package io.github.gufeczek.crochet.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.koin.core.annotation.Single

interface CounterRepository {
    suspend fun create()
    fun getCounter(id: String): Flow<Int>
    suspend fun incrementCount()
    suspend fun decrementCount()
}

@Single
class InMemoryCounterRepository : CounterRepository {
    private val _counter: MutableStateFlow<Int> = MutableStateFlow(0)

    override suspend fun create() = Unit

    override fun getCounter(id: String): Flow<Int> {
        return _counter.asStateFlow()
    }

    override suspend fun incrementCount() {
        _counter.update { it + 1 }
    }

    override suspend fun decrementCount() {
        _counter.update { it - 1 }

    }

}