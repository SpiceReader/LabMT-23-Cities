package by.brstu.redlabrat.testlistapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.brstu.redlabrat.testlistapp.api.SearchResultsMain
import by.brstu.redlabrat.testlistapp.db.CityDao
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleOnSubscribe
import io.reactivex.rxjava3.schedulers.Schedulers

class MyListAdapter(private var listOfItems: List<SearchResultsMain>,
                    private val dbDao: CityDao?,
                    private val onItemClickListener: MyItemClickListener
) : RecyclerView.Adapter<MyListAdapter.MyTestItemViewHolder>() {

    interface MyItemClickListener {
        fun onMyItemClick(selectedStr: SearchResultsMain)
        fun addToFavorites(selectedCity: SearchResultsMain)
    }

    fun setNewListOfItems(newList: List<SearchResultsMain>) {
        listOfItems = newList
        notifyDataSetChanged()
    }

    class MyTestItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.listItemTextView)
        val button: ImageButton = itemView.findViewById(R.id.favoritesImageButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyTestItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MyTestItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listOfItems.size
    }

    override fun onBindViewHolder(holder: MyTestItemViewHolder, position: Int) {
        val city = listOfItems[position]
        holder.textView.text = city.city
        holder.itemView.setOnClickListener {
            onItemClickListener.onMyItemClick(city)
        }
        holder.button.setOnClickListener {
            onItemClickListener.addToFavorites(city)
            this.notifyItemChanged(position)
        }
        findCityById(city.id_db.toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { isCityFavorite, error ->
                if (isCityFavorite) {
                    holder.button.setImageResource(R.drawable.heart_red)
                }
            }
    }

    private fun findCityById(id: String): Single<Boolean> {
        return Single.create<Boolean> {
            SingleOnSubscribe<Boolean> { single ->
                val city = dbDao?.getCityById(id)
                if (city != null) {
                    single.onSuccess(true)
                } else {
                    single.onSuccess(false)
                }
            }
                .subscribe(it)
        }
    }
}