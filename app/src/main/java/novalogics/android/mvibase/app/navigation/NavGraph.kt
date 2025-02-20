package novalogics.android.mvibase.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import novalogics.android.mvibase.feature.details.DetailsScreen
import novalogics.android.mvibase.feature.home.presentation.HomeScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Screens.Home.route,
        modifier = modifier
    ) {
        composable(Screens.Home.route) {
            HomeScreen(navController, onNavigateToDetail = {})
        }
        composable(Screens.Details.route) {
            DetailsScreen(navController)
        }
    }
}
