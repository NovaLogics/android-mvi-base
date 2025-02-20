package novalogics.android.mvibase.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import novalogics.android.mvibase.feature.details.DetailsScreen
import novalogics.android.mvibase.feature.home.HomeScreen


@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.Home.route
    ) {
        composable(Screens.Home.route) {
            HomeScreen(navController)
        }
        composable(Screens.Details.route) {
            DetailsScreen(navController)
        }
    }
}
