package com.example.rentorbuy.ui

import androidx.lifecycle.ViewModel
import com.example.rentorbuy.data.BaseUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.math.ceil

class BaseViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(BaseUiState())
    val uiState: StateFlow<BaseUiState> = _uiState.asStateFlow()
    fun calcRes(rentPrice: Float, buyPrice: Float) {
        var modZero = false
        if (buyPrice % rentPrice == 0f) {
            modZero = true
        }

        _uiState.update { currentState ->
            currentState.copy(
                rentPrice = rentPrice,
                buyPrice = buyPrice,
                breakPoint = calcBreakpoint(rentPrice, buyPrice),
                modZero = modZero
            )
        }
    }

    private fun calcBreakpoint(rentPrice: Float, buyPrice: Float): Int {
        return ceil(buyPrice / rentPrice).toInt()
    }
}