package novalogics.android.mvibase.feature.home.data.mapper

import novalogics.android.mvibase.feature.home.data.local.entity.QuoteEntity
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

fun Quote.toEntity(): QuoteEntity {
    return QuoteEntity(
        id = this.id,
        author = this.author,
        content = this.content,
        tags = this.tags,
        authorSlug = this.authorSlug,
        length = this.length,
        dateAdded = this.dateAdded,
        dateModified = this.dateModified
    )
}

fun QuoteEntity.toDomain(): Quote {
    return Quote(
        id = this.id,
        author = this.author,
        content = this.content,
        tags = this.tags,
        authorSlug = this.authorSlug,
        length = this.length,
        dateAdded = this.dateAdded,
        dateModified = this.dateModified
    )
}
