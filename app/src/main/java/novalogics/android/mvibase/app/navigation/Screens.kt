package novalogics.android.mvibase.app.navigation

import novalogics.android.mvibase.app.navigation.NavigationArgs.ITEM_ID

sealed class Screens(val route: String) {
    data object Home : Screens(NavigationRoutes.HOME)
    data object Details : Screens(NavigationRoutes.DETAILS)

    data class ItemDetails(val itemId: String) : Screens("${NavigationRoutes.DETAILS}/$itemId") {
        companion object {
            fun createRoute(itemId: String) = "${NavigationRoutes.DETAILS}/$itemId"
        }
    }
}

object NavigationRoutes {
    const val HOME = "home"
    const val DETAILS = "detail"
    const val ITEM_DETAIL = "$DETAILS/{$ITEM_ID}"
}

object NavigationArgs {
    const val ITEM_ID = "itemId"
    const val ITEM_JSON = "itemJson"
}
