package novalogics.android.mvibase.feature.home.presentation

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import novalogics.android.mvibase.R
import novalogics.android.mvibase.core.presentation.components.CustomProgressIndicator
import novalogics.android.mvibase.core.presentation.components.ErrorMessageCard
import novalogics.android.mvibase.core.presentation.theme.MVIBaseTheme
import novalogics.android.mvibase.feature.home.domain.model.Quote
import novalogics.android.mvibase.feature.home.presentation.components.QuoteItem
import novalogics.android.mvibase.feature.home.presentation.state.HomeEffect
import novalogics.android.mvibase.feature.home.presentation.state.HomeIntent
import novalogics.android.mvibase.feature.home.presentation.state.HomeUiState

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel<HomeViewModel>(),
    onNavigateToDetail: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    HandleSideEffects(viewModel, onNavigateToDetail)

    ScreenUiContent(
        uiState = uiState,
        onItemClick = { itemId ->
            viewModel.handleIntent(HomeIntent.OnItemClick(itemId))
        },
        onFetchOnlineQuotes = {
            viewModel.handleIntent(HomeIntent.FetchLiveQuotes)
        }
    )
}

/**
 * Observes and handles side effects such as navigation and displaying messages.
 */
@Composable
fun HandleSideEffects(
    viewModel: HomeViewModel,
    onNavigateToDetail: () -> Unit
) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.uiEffect.collect { effect ->
            when (effect) {
                is HomeEffect.NavigateToItemDetail -> {
                    onNavigateToDetail()
                }
                is HomeEffect.ShowMessage -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenUiContent(
    uiState: HomeUiState,
    onItemClick: (String) -> Unit,
    onFetchOnlineQuotes: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.home_screen_title),
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                modifier = Modifier.background(MaterialTheme.colorScheme.surface)
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Column {
                AppHeader(onFetchOnlineQuotes)
                ItemList(uiState.quotes, onItemClick)
            }

            when {
                uiState.isLoading -> CustomProgressIndicator()
                uiState.error.isNotBlank() -> ErrorMessageCard(uiState.error)
            }
        }
    }
}

/**
 * Displays the app header with a refresh button.
 */
@Composable
fun AppHeader(
    onFetchOnlineQuotes: () -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Daily Quotes",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.displayLarge.copy(fontWeight = FontWeight.Light),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onFetchOnlineQuotes,
            modifier = Modifier
                .padding(horizontal = 36.dp)
                .fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_autorenew),
                tint = MaterialTheme.colorScheme.onPrimary,
                contentDescription = "Refresh Quotes"
            )
            Text(
                text = "Refresh Quotes",
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
    }
}

/**
 * Displays a list of quotes with click support.
 */
@Composable
fun ItemList(items: List<Quote>, onItemClick: (String) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(items) { quote ->
            QuoteItem(quote, onItemClick)
        }
    }
}

@Preview(
    name = "MODE LIGHT",
    showBackground = true,
    uiMode = UI_MODE_NIGHT_NO
)
@Preview(
    name = "MODE NIGHT",
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun HomePreview() {

    val quotes = listOf(
        Quote(
            id = "pNnzE7wpM0W",
            author = "Dee Hock",
            content = "An organization, no matter how well designed, is only as good as the people who live and work in it.",
            tags = arrayListOf("Business"),
            authorSlug = "dee-hock",
            length = 100,
            dateAdded = "2022-07-06",
            dateModified = "2023-04-14"
        ),
        Quote(
            id = "Vs-4YEGn",
            author = "Simone Weil",
            content = "I can, therefore I am.",
            tags = arrayListOf("Inspirational"),
            authorSlug = "simone-weil",
            length = 22,
            dateAdded = "2020-03-11",
            dateModified = "2023-04-14"
        ),
    )

    val uiState = HomeUiState(
        isLoading = false,
        quotes = quotes,
        error = ""
    )

    MVIBaseTheme {
        ScreenUiContent(
            uiState = uiState,
            onItemClick = {},
            onFetchOnlineQuotes = {},
        )
    }
}
