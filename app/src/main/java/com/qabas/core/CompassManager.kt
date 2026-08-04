package com.qabas.core

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class CompassState(
    val stageDescription: String = "مرحلة التأسيس",
    val currentUnitId: String? = null
)

object CompassManager {
    private val _state = MutableStateFlow(CompassState())
    val state: StateFlow<CompassState> = _state

    fun updateState(description: String, unitId: String?) {
        _state.value = CompassState(description, unitId)
    }
}
