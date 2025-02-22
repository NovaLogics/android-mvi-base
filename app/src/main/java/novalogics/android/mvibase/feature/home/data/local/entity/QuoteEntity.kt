package novalogics.android.mvibase.feature.home.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quotes")
data class QuoteEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "author")
    val author: String,

    @ColumnInfo(name = "content")
    val content: String,

    @ColumnInfo(name = "tags")
    val tags: List<String>,

    @ColumnInfo(name = "author_slug")
    val authorSlug: String,

    @ColumnInfo(name = "length")
    val length: Int = 0,

    @ColumnInfo(name = "date_added")
    val dateAdded: String,

    @ColumnInfo(name = "date_modified")
    val dateModified: String
)
