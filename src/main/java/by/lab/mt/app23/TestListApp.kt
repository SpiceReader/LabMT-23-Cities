package by.brstu.redlabrat.testlistapp

import android.app.Application
import androidx.room.Room
import by.brstu.redlabrat.testlistapp.api.OpenAqAPI
import by.brstu.redlabrat.testlistapp.db.CitiesDatabase
import by.brstu.redlabrat.testlistapp.db.CityDao
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TestListApp : Application() {

    lateinit var aqApi: OpenAqAPI
    lateinit var CityDao: CityDao

    override fun onCreate() {
        super.onCreate()
        aqApi = Retrofit.Builder()
            .baseUrl("https://api.openaq.org/v1/") // api.openweathermap.org/data/2.5/weather
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(OpenAqAPI::class.java)


        CityDao = Room.databaseBuilder(
            applicationContext,
            CitiesDatabase::class.java,
            "city.db")
            .build()
            .getFavoritesDao()
    }
}
