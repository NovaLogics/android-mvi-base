package novalogics.android.mvibase.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import novalogics.android.mvibase.feature.details_sample.DetailsScreen
import novalogics.android.mvibase.feature.home.presentation.HomeScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Home.route,
        modifier = modifier
    ) {
        composable(Screens.Home.route) {
            HomeScreen(onNavigateToDetail = { screen ->
                navController.navigate(screen.route)
            })
        }
        composable(
            route = NavigationRoutes.ITEM_DETAIL,
            arguments = listOf(
                navArgument(NavigationArgs.ITEM_ID) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString(NavigationArgs.ITEM_ID) ?: ""
            DetailsScreen(
                itemId = itemId,
                navController = navController,
            )
        }
    }
}
