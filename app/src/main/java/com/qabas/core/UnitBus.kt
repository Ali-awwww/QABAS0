package com.qabas.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

// الرسائل التي تمر عبر الحافلة
data class BusMessage(
    val senderId: String,
    val action: String,
    val priority: MessagePriority = MessagePriority.NORMAL,
    val payload: Any? = null
)

// حافلة الوحدات (Unit Bus) - النواة
object UnitBus {
    private val _bus = MutableSharedFlow<BusMessage>()
    val bus = _bus.asSharedFlow()

    fun publish(message: BusMessage) {
        CoroutineScope(Dispatchers.IO).launch {
            _bus.emit(message)
        }
    }
}
