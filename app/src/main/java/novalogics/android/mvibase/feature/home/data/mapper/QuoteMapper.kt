package novalogics.android.mvibase.feature.home.data.mapper

import novalogics.android.mvibase.feature.home.data.model.QuoteDto
import novalogics.android.mvibase.feature.home.domain.model.Quote

fun QuoteDto.toDomain(): Quote {
    return Quote(
        id = this.id,
        author = this.author,
        content = this.content,
        tags = this.tags,
        authorSlug = this.authorSlug,
        length = this.length ?: 0,
        dateAdded = this.dateAdded,
        dateModified = this.dateModified
    )
}
