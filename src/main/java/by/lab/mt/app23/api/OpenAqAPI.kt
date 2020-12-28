package by.brstu.redlabrat.testlistapp.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenAqAPI {

    @GET("cities/")
    fun getResultsForString(
        @Query("country") cityName: String,
        @Query("limit") limit: Number = 13
    ): Single<SearchResult>
}