package by.brstu.redlabrat.testlistapp.api

import com.google.gson.annotations.SerializedName

data class SearchResult (
    @SerializedName("results")
    val results: List<SearchResultsMain>

)


