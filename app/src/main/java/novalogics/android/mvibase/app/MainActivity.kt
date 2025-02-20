package novalogics.android.mvibase.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import novalogics.android.mvibase.app.navigation.AppNavigation
import novalogics.android.mvibase.core.presentation.theme.MVIBaseTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MVIBaseTheme {
                AppNavigation()
            }
        }
    }
}
