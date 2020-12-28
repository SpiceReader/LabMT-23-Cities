package by.brstu.redlabrat.testlistapp.db

import androidx.room.*
import by.brstu.redlabrat.testlistapp.api.SearchResultsMain

@Dao
interface CityDao {

    @Query("select * from search_cities")
    fun getAllSavedRecords(): List<SearchResultsMain>

    @Query("select * from search_cities where iddb == :id")
    fun getCityById(id: String): SearchResultsMain?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCityToFavorites(city: SearchResultsMain)

    @Delete
    fun removeFromFavorites(city: SearchResultsMain)
}