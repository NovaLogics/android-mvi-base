package novalogics.android.mvibase.feature.home.presentation.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import novalogics.android.mvibase.core.presentation.theme.MVIBaseTheme
import novalogics.android.mvibase.feature.home.domain.model.Quote

@Composable
fun QuoteItem(
    quote: Quote,
    onItemClick: (String) -> Unit
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onItemClick(quote.id) }
    ) {
        Column {
            Text(
                text = "\"${quote.content}\"",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.primary,
            )
            Text(
                text = "- ${quote.author}",
                modifier = Modifier.padding(horizontal = 16.dp).padding(bottom = 16.dp),
                style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.ExtraBold),
                color = MaterialTheme.colorScheme.tertiary,
            )
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
fun QuoteItemPreview() {
    val quote =  Quote(
        id = "pNnzE7wpM0W",
        author = "Dee Hock",
        content = "An organization, no matter how well designed, is only as good as the people who live and work in it.",
        tags = arrayListOf("Business"),
        authorSlug = "dee-hock",
        length = 100,
        dateAdded = "2022-07-06",
        dateModified = "2023-04-14"
    )

    MVIBaseTheme {
        QuoteItem(
            quote = quote,
            onItemClick = {}
        )
    }
}
