package adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dto.Editors
import timefighter.raywenderlich.haberapp.R

class EditorAdapter(private val context: Context, private val editorList: List<Editors>):RecyclerView.Adapter<EditorViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EditorViewHolder {
        return EditorViewHolder(LayoutInflater.from(context).inflate(R.layout.editor_items,parent,false) as ViewGroup)
    }

    override fun getItemCount(): Int {
       return editorList.size
    }

    override fun onBindViewHolder(holder: EditorViewHolder, position: Int) {
        return holder.loadData(editorList[position])
    }
}