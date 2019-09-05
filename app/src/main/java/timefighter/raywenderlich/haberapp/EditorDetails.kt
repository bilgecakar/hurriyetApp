package timefighter.raywenderlich.haberapp

import android.annotation.TargetApi
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import dto.Editors
import network.RetrofitClient
import network.Service
import retrofit2.Call
import retrofit2.Response
import java.util.*

class EditorDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor_details)
        val tBar=findViewById<Toolbar>(R.id.t_bar)
        setSupportActionBar(tBar)
        tBar.inflateMenu(R.menu.main_menu)
        tBar.setNavigationIcon(R.drawable.left_arro)
        tBar.setNavigationOnClickListener {
            finish()
        }
        val cId=intent.getIntExtra("Id",0)
        val service=RetrofitClient.serviceBuilder().create(Service::class.java)
        service.getAuthorDetail(cId.toString()).enqueue(object :retrofit2.Callback<Editors>
        {
            override fun onFailure(call: Call<Editors>, t: Throwable) {
            }
            override fun onResponse(call: Call<Editors>, response: Response<Editors>) {
                editorDetail(response.body()!!)

            }
        })
    }

    @TargetApi(Build.VERSION_CODES.N)
    fun editorDetail(editor:Editors)
    {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        var convertedDate: Date? = null
        var formattedDate: String? = null
        convertedDate = sdf.parse(editor.CreatedDate)
        formattedDate = SimpleDateFormat("E, dd MMM yyyy HH:mm a ").format(convertedDate)
        val copyTitle=findViewById<TextView>(R.id.copyEditorTitle)
        val copyName=findViewById<TextView>(R.id.copyEditorName)
        val copyCreatedDate=findViewById<TextView>(R.id.copyC_date)
        val copyDes=findViewById<WebView>(R.id.copyDcs)
        copyDes.settings.javaScriptEnabled=true
        copyDes.loadData(editor.Text,"text/html","utf-8")
        copyTitle.text=editor.Title
        copyName.text=editor.Fullname
        copyCreatedDate.text=formattedDate
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.second_menu,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id= item.itemId
        if(id==R.id.share)
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
