package by.brstu.redlabrat.testlistapp.api

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "search_cities")
data class SearchResultsMain (

    @SerializedName("country")
    @ColumnInfo(name = "country")
    val country: String,

    @SerializedName("name")
    @ColumnInfo(name = "name")
    val name: String,

    @SerializedName("city")
    @ColumnInfo(name = "city")
    val city: String,

    @SerializedName("count")
    @ColumnInfo(name = "count")
    val count: Int,

    @PrimaryKey
    @SerializedName("locations")
    @ColumnInfo(name = "iddb")
    val id_db: Int
)