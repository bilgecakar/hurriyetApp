package adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dto.Yazarlar
import timefighter.raywenderlich.haberapp.R
import timefighter.raywenderlich.haberapp.yazarlarActivity

class YazarlarAdapter(private val context: yazarlarActivity, private val yazarlarList:List<Yazarlar>):RecyclerView.Adapter<YazarlarViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YazarlarViewHolder {
       return YazarlarViewHolder(LayoutInflater.from(context).inflate(R.layout.yazarlar_list,parent,false) as ViewGroup)
    }
    override fun getItemCount(): Int {
        return yazarlarList.size
    }
    override fun onBindViewHolder(holder: YazarlarViewHolder, position: Int) {
        return holder.loadData(yazarlarList[position])
    }


}