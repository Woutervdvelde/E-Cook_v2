package com.woutervandervelde.e_cook.presentation.utils

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

import java.util.concurrent.atomic.AtomicBoolean

private const val EVENT_DEBOUNCE = 500L

@Stable
class MutableEventFlow<T: Any> private constructor(
    private val backingFlow: MutableStateFlow<Event<T>?>,
) : MutableStateFlow<Event<T>?> by backingFlow {

    constructor() : this(MutableStateFlow(null))

    fun sendEvent(data: T, ignoreDebounce: Boolean = false) {
        value?.let { event ->
            if (
                !ignoreDebounce &&
                event.peek() == data &&
                System.currentTimeMillis() - event.timestamp < EVENT_DEBOUNCE
            ) return@let
        }
        value = Event(data)
    }

    fun asEventFlow() = EventFlow(this)
}

@Stable
class EventFlow<out T: Any>(
    private val mutable: MutableEventFlow<T>,
) : StateFlow<Event<T>?> by mutable {

}

@Immutable
data class Event<out T: Any>(
    private val data: T,
) {
    val timestamp: Long = System.currentTimeMillis()

    private val _isRetrieved = AtomicBoolean(false)
    val isRetrieved: Boolean
        get() = _isRetrieved.get()

    fun peek(): T = data

    fun retrieve(): T? =
        if (!_isRetrieved.getAndSet(true)) data else null

    override fun hashCode(): Int {
        var result = data.hashCode()
        result = 31 * result + _isRetrieved.hashCode()
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Event<*>

        if (data != other.data) return false
        return isRetrieved == other.isRetrieved
    }
}