package adapter

import android.annotation.TargetApi
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dto.Editors
import timefighter.raywenderlich.haberapp.EditorDetails
import timefighter.raywenderlich.haberapp.R
import java.util.*

class EditorViewHolder(private val viewGroup: ViewGroup):RecyclerView.ViewHolder(viewGroup) {

    val editorTitle by lazy { itemView.findViewById<TextView>(R.id.editorTitle) }
    val name by lazy { itemView.findViewById<TextView>(R.id.editorName)}
    val crtDtae by lazy { itemView.findViewById<TextView>(R.id.c_date) }

    @TargetApi(Build.VERSION_CODES.N)
    fun loadData(editor:Editors)
    {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        var convertedDate: Date? = null
        var formattedDate: String? = null
        convertedDate = sdf.parse(editor.CreatedDate)
        formattedDate = SimpleDateFormat("E, dd MMM yyyy HH:mm a ").format(convertedDate)
        editorTitle.text=editor.Title
        name.text=editor.Fullname
        crtDtae.text=formattedDate

        viewGroup.setOnClickListener()
        {
            val intent= Intent(viewGroup.context,EditorDetails::class.java)
            intent.putExtra("Id",editor.Id)
            viewGroup.context.startActivity(intent)
        }
    }




}