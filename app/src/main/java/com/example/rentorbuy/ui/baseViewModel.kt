package com.example.rentorbuy.ui

import androidx.lifecycle.ViewModel
import com.example.rentorbuy.data.baseUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class baseViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(baseUiState())
    val uiState: StateFlow<baseUiState> = _uiState.asStateFlow()

    fun calcRes(rentPrice: Float, buyPrice: Float) {
        _uiState.update { currentState ->
            currentState.copy(
                rentPrice = rentPrice,
                buyPrice = buyPrice,
                breakPoint = calcBreakpoint(rentPrice, buyPrice)
            )
        }
    }

    private fun calcBreakpoint(rentPrice: Float, buyPrice: Float): Float {
        return buyPrice / rentPrice
    }
}