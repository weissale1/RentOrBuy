package com.example.rentorbuy.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.rentorbuy.R
import com.example.rentorbuy.ui.theme.InputScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rentorbuy.ui.theme.OutputScreen

enum class RentOrBuyScreen(@StringRes val title: Int) {
    Input(title = R.string.input_screen),
    Result(title = R.string.result_screen)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RentOrBuyApp(
    viewModel: baseViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = RentOrBuyScreen.valueOf(backStackEntry?.destination?.route ?: RentOrBuyScreen.Input.name)

    Scaffold {innerPadding ->
        val uiState by viewModel.uiState.collectAsState()
        NavHost(
            navController = navController,
            startDestination = RentOrBuyScreen.Input.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = RentOrBuyScreen.Input.name) {
                InputScreen(
                    onNextButtonClicked = { rentPrice, buyPrice ->
                        viewModel.calcRes(rentPrice, buyPrice)
                        navController.navigate(RentOrBuyScreen.Result.name)
                    }
                )
            }
            composable(route = RentOrBuyScreen.Result.name) {
                OutputScreen(
                    rentalPrice = uiState.rentPrice,
                    buyPrice = uiState.buyPrice,
                    calcRes = uiState.breakPoint,
                    onBackButtonClicked = {
                        navController.navigate(RentOrBuyScreen.Input.name)
                    }
                )
            }
        }
    }


}
