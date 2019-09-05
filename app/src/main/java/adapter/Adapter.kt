package adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dto.Veri
import timefighter.raywenderlich.haberapp.R

class Adapter(private val context: Context, private var dataList: List<Veri>):RecyclerView.Adapter<ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.news_fragment_item,parent,false) as ViewGroup)
    }
    override fun getItemCount(): Int {
        return dataList.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.matchData(dataList[position])
    }
}