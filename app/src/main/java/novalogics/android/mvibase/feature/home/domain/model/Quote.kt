package novalogics.android.mvibase.feature.home.domain.model

data class Quote(
    val id: String,
    val author: String,
    val content: String,
    val tags: List<String>,
    val authorSlug: String,
    val length: Int,
    val dateAdded: String,
    val dateModified: String
)
