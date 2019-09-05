package timefighter.raywenderlich.haberapp

import adapter.SliderDetailAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.github.chrisbanes.photoview.PhotoView
import dto.Veri
import me.relex.circleindicator.CircleIndicator
import network.RetrofitClient
import network.Service
import retrofit2.Call
import retrofit2.Response

class SliderDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slider_detail)
        val cindicator = findViewById<CircleIndicator>(R.id.circleIndicator)
        val img = findViewById<PhotoView>(R.id.photoView)
        val pager = findViewById<ViewPager>(R.id.pager)
        val service = RetrofitClient.serviceBuilder().create(Service::class.java)
        val cId= intent.getIntExtra("id",0)
        service.getImageDetail(cId.toString()).enqueue(object :retrofit2.Callback<Veri>
        {
            override fun onFailure(call: Call<Veri>, t: Throwable) {
            }
            override fun onResponse(call: Call<Veri>, response: Response<Veri>) {
                val vAdapter= SliderDetailAdapter(this@SliderDetail,response.body()!!)
                pager.adapter=vAdapter
                cindicator.setViewPager(pager)
            }
        })
    }
}
