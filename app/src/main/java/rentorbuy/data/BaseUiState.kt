package rentorbuy.data

data class BaseUiState(
    val rentPrice: Float = 0f,
    val buyPrice: Float = 0.0f,
    val breakPoint: Int = 0,
    val modZero: Boolean = false
)
