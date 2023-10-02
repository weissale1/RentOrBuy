package com.example.rentorbuy.ui

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
//import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.rentorbuy.R
import androidx.lifecycle.viewmodel.compose.viewModel

enum class RentOrBuyScreen {
    Input,
    Result,
    About
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RentOrBuyApp(
    viewModel: BaseViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    //val backStackEntry by navController.currentBackStackEntryAsState()
    //val currentScreen = RentOrBuyScreen.valueOf(backStackEntry?.destination?.route ?: RentOrBuyScreen.Input.name)

    fun getStartText(modZero: Boolean): Int {
        return if (modZero) {
            R.string.res_buying_emph2
        } else {
            R.string.res_buying_emph1
        }
    }

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
                    },
                    onAboutButtonClicked = {
                        navController.navigate(RentOrBuyScreen.About.name)
                    }
                )
            }
            composable(route = RentOrBuyScreen.Result.name) {
                OutputScreen(
                    rentalPrice = uiState.rentPrice,
                    buyPrice = uiState.buyPrice,
                    breakPoint = uiState.breakPoint,
                    resTextStart = getStartText(uiState.modZero),
                    onBackButtonClicked = {
                        navController.popBackStack(RentOrBuyScreen.Input.name, inclusive = false)
                    }
                )
            }
            composable(route = RentOrBuyScreen.About.name) {
                AboutScreen(
                    aboutText = R.string.about_text,
                    onBackButtonClicked = {
                        navController.popBackStack(RentOrBuyScreen.Input.name, inclusive = false)
                    }
                )
            }
        }
    }
}
