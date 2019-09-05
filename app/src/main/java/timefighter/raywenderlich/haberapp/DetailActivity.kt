package timefighter.raywenderlich.haberapp

import android.annotation.TargetApi
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import dto.File
import dto.Veri
import network.RetrofitClient
import network.Service
import retrofit2.Call
import retrofit2.Response
import java.util.*


@Suppress("RedundantOverride")
class DetailActivity : AppCompatActivity() {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val tBar=findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(tBar)
        tBar.inflateMenu(R.menu.main_menu)
        tBar.setNavigationIcon(R.drawable.left_arro)

        tBar.setNavigationOnClickListener {
            finish()
        }

        val service = RetrofitClient.serviceBuilder().create(Service::class.java)
        val copyId=intent.getIntExtra("Id",0)
        service.getId(copyId.toString()).enqueue(object : retrofit2.Callback<Veri>
        {
            override fun onFailure(call: Call<Veri>, t: Throwable) {
                //Toast.makeText(this@DetailActivity,"Failure",Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Veri>, response: Response<Veri>) {
                // Toast.makeText(this@DetailActivity,"Success",Toast.LENGTH_SHORT).show()
                setDetails(response.body()!!)

                val loading = findViewById<ProgressBar>(R.id.loading)

                Thread(Runnable {
                    fun run()
                    {
                        for (x in 0 until 100)
                        {
                            Thread.sleep(500)
                        }
                    }
                }).start()
                loading.visibility=View.GONE

            }
        })
        setTitle("Haber Detayı")

    }


    @TargetApi(Build.VERSION_CODES.N)
    fun setDetails(veri: Veri)
    {

        val titleTxt=findViewById<TextView>(R.id.copyTitle)
        val decTxt =findViewById<TextView>(R.id.copyDescription)
        val dateTxt = findViewById<TextView>(R.id.copyCreatedDate)
        val imgTxt =findViewById<ImageView>(R.id.copyImg)
        titleTxt.text=veri.Title
        decTxt.text=veri.Description

        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        var convertedDate: Date? = null
        var formattedDate: String? = null

        convertedDate = sdf.parse(veri.CreatedDate)
        formattedDate = SimpleDateFormat("E, dd MMM yyyy HH:mm a ").format(convertedDate)
        dateTxt.text=formattedDate

        fun loadImage(file:File)
        {
            Glide.with(this@DetailActivity).load(file.FileUrl).into(imgTxt)
        }
        loadImage(veri.Files[0])

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
            return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id= item.itemId
        if(id==R.id.shareId)
        {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            val shareBody = "Application Link: "
            intent.putExtra(Intent.EXTRA_SUBJECT, "App link")
            intent.putExtra(Intent.EXTRA_TEXT, shareBody)
            startActivity(Intent.createChooser(intent, "Share App Link Via :"))
        }

        return super.onOptionsItemSelected(item)
    }
}

