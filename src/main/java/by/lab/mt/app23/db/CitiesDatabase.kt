package by.brstu.redlabrat.testlistapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import by.brstu.redlabrat.testlistapp.api.SearchResultsMain

@Database(entities = [SearchResultsMain::class], version = 1)
abstract class CitiesDatabase : RoomDatabase() {
    abstract fun getFavoritesDao(): CityDao
}