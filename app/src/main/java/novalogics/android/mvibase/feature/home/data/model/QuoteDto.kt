package novalogics.android.mvibase.feature.home.data.model

import com.google.gson.annotations.SerializedName

data class QuoteDto(
    @SerializedName("_id") val id: String,
    @SerializedName("author") val author: String,
    @SerializedName("content") val content: String,
    @SerializedName("tags") val tags: List<String>,
    @SerializedName("authorSlug") val authorSlug: String,
    @SerializedName("length") val length: Int?,
    @SerializedName("dateAdded") val dateAdded: String,
    @SerializedName("dateModified") val dateModified: String
)
