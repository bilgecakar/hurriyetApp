@file:Suppress("RedundantOverride")

package timefighter.raywenderlich.haberapp

import adapter.Adapter
import adapter.ViewPagerAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import dto.Veri
import me.relex.circleindicator.CircleIndicator
import network.RetrofitClient
import network.Service
import retrofit2.Call
import retrofit2.Response

class NewsFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      val view= inflater.inflate(R.layout.news_fragment,container,false)

        val indicator = view.findViewById<CircleIndicator>(R.id.indicator)
        val viewPager= view.findViewById<ViewPager>(R.id.view_pager)
        val recyclerview= view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerview.layoutManager=LinearLayoutManager(context)
        recyclerview.hasFixedSize()
        val service =RetrofitClient.serviceBuilder().create(Service::class.java)
        service.getVeri().enqueue(object : retrofit2.Callback<List<Veri>>
        {
            override fun onFailure(call: Call<List<Veri>>, t: Throwable) {
            }
            override fun onResponse(call: Call<List<Veri>>, response: Response<List<Veri>>) {
                recyclerview.adapter = context?.let { Adapter(it,response.body()!!) }
            }
        })

        service.getImage().enqueue(object :retrofit2.Callback<List<Veri>>
        {
            override fun onFailure(call: Call<List<Veri>>, t: Throwable) {
            }
            override fun onResponse(call: Call<List<Veri>>, response: Response<List<Veri>>) {
                val vAdapter=context?.let { ViewPagerAdapter(it,response.body()!!) }
                viewPager.adapter=vAdapter
                indicator.setViewPager(viewPager)
            }
        })
        activity!!.setTitle("Haberler")
        return view
    }
}
