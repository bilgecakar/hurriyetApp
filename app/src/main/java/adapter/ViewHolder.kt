package adapter

import android.annotation.TargetApi
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dto.File
import dto.Veri
import timefighter.raywenderlich.haberapp.DetailActivity
import timefighter.raywenderlich.haberapp.R
import java.util.*

class ViewHolder(private val viewGroup: ViewGroup) : RecyclerView.ViewHolder(viewGroup) {

    private val txtTitle by lazy { itemView.findViewById<TextView>(R.id.title) }
    private val imgFile by lazy { itemView.findViewById<ImageView>(R.id.img) }
    private val cDate by lazy { itemView.findViewById<TextView>(R.id.createdDate) }

    @TargetApi(Build.VERSION_CODES.N)
    fun matchData(veriDto: Veri) {

        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        var convertedDate: Date? = null
        var formattedDate: String? = null
        convertedDate = sdf.parse(veriDto.CreatedDate)
        formattedDate = SimpleDateFormat("E, dd MMM yyyy HH:mm a ").format(convertedDate)
        txtTitle.text = veriDto.Title
        cDate.text = formattedDate

        if (veriDto.Files.isNotEmpty())
            showImage(veriDto.Files[0])

        viewGroup.setOnClickListener {
            val intent = Intent(viewGroup.context, DetailActivity::class.java)
            intent.putExtra("Id",veriDto.Id)
            viewGroup.context.startActivity(intent)
        }
    }

    fun showImage(img: File) {
      Glide.with(itemView.context).load(img.FileUrl).into(imgFile)
    }





}


