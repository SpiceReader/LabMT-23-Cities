package by.brstu.redlabrat.testlistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.brstu.redlabrat.testlistapp.api.SearchResultsMain

class MainActivity : AppCompatActivity(), MyListAdapter.MyItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        val fragment = ListFragment.newInstance("")
        supportFragmentManager.beginTransaction()
            .add(R.id.mainFrame, fragment)
            .commit()
    }

    override fun onMyItemClick(selectedCity: SearchResultsMain) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainFrame, DetailsFragment.newInstance(selectedCity.id_db.toString()))
            .addToBackStack(null)
            .commit()
    }

    override fun addToFavorites(selectedCity: SearchResultsMain) {
        (application as? TestListApp)?.let { app ->
            Thread {
                if (app.CityDao.getCityById(selectedCity.id_db.toString()) != null) {
                    app.CityDao.removeFromFavorites(selectedCity)
                } else {
                    app.CityDao.addCityToFavorites(selectedCity)
                }
            }.start()
        }
    }

    fun goToFavorites() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainFrame, FavoritesListFragment())
            .addToBackStack(null)
            .commit()
    }
}