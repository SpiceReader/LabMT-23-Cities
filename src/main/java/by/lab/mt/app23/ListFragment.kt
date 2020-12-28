package by.brstu.redlabrat.testlistapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment() {

    private var argument1: String? = null
    private lateinit var listAdapter: MyListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        argument1 = arguments?.getString(ARG_PARAM1)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val gson = GsonBuilder().create()


        super.onViewCreated(view, savedInstanceState)
        listAdapter = MyListAdapter(emptyList(),
            (activity?.application as? TestListApp)?.CityDao,
            requireActivity() as MyListAdapter.MyItemClickListener)
        recyclerView.adapter = listAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        searchButton.setOnClickListener {
            (activity?.application as? TestListApp)?.aqApi?.let {
                it.getResultsForString(searchText.text.toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { results, error ->
                        listAdapter.setNewListOfItems(results.results)
                    }
            }
        }
        goToFavoritesButton.setOnClickListener {
            (requireActivity() as MainActivity).goToFavorites()
        }
    }



    companion object {
        private const val ARG_PARAM1 = "argument parameter 1"
        @JvmStatic
        fun newInstance(param1: String) =
            ListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
        }
    }
}