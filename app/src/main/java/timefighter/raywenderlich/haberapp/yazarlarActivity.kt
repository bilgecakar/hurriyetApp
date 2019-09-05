package timefighter.raywenderlich.haberapp

import adapter.YazarlarAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import dto.Yazarlar
import kotlinx.android.synthetic.main.activity_yazarlar.*
import network.RetrofitClient
import network.Service
import retrofit2.Call
import retrofit2.Response

class yazarlarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_yazarlar)
        yazarReclerview.layoutManager= LinearLayoutManager(this)
        val service= RetrofitClient.serviceBuilder().create(Service::class.java)
        service.getWriters().enqueue(object: retrofit2.Callback<List<Yazarlar>>
        {
            override fun onFailure(call: Call<List<Yazarlar>>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
            override fun onResponse(call: Call<List<Yazarlar>>, response: Response<List<Yazarlar>>) {
                yazarReclerview.adapter= YazarlarAdapter(this@yazarlarActivity,response.body()!!)
            }
        })
        setTitle("Yazarlar")
    }
}
