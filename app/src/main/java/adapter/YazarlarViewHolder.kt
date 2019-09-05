package adapter

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dto.Files
import dto.Yazarlar
import timefighter.raywenderlich.haberapp.R

class YazarlarViewHolder(private val viewGroup: ViewGroup):RecyclerView.ViewHolder(viewGroup) {
    private val yazarName by lazy { itemView.findViewById<TextView>(R.id.yazarName) }
    private val yazarImage by lazy { itemView.findViewById<ImageView>(R.id.yazarImage) }

    fun loadData(yazar:Yazarlar)
    {
        yazarName.text=yazar.Fullname
        if (yazar.Files.isNotEmpty())
        {
            loadImage(yazar.Files[0])
        }else
        {
            yazarImage.setImageResource(R.drawable.student)
        }

    }
    fun loadImage(file:Files)
    {
        Glide.with(itemView.context).load(file.FileUrl).into(yazarImage)
    }

}