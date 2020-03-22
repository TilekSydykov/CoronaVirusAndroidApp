package kg.koronastaff.staffapp.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kg.koronastaff.staffapp.R
import kg.koronastaff.staffapp.adapters.NewsAdapter
import kg.koronastaff.staffapp.ui.FragmentWithStat
import java.util.*

class NewsFragment : FragmentWithStat() {
    private var recyclerView: RecyclerView? = null
    private lateinit var mAdapter: NewsAdapter
    private var viewManager: RecyclerView.LayoutManager? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {

        super.onCreateView(inflater, container, savedInstanceState)

        val rootView = inflater.inflate(R.layout.fragment_news, container, false)
        viewManager = LinearLayoutManager(context)

        mAdapter = NewsAdapter(ArrayList(), activity!!)

        recyclerView = rootView.findViewById<RecyclerView>(R.id.news_list_recycler).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = mAdapter
        }

        super.coronaViewModel.getNews()?.subscribe {
            mAdapter.update(it.results!!)
        }

        return rootView
    }
}