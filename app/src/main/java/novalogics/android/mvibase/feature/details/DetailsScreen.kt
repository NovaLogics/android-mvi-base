package novalogics.android.mvibase.feature.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import novalogics.android.mvibase.app.navigation.Screens

@Composable
fun DetailsScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Details Screen", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Button(onClick = { navController.navigate(Screens.Home.route) }) {
            Text("Go to Home")
        }
    }
}
