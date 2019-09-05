package timefighter.raywenderlich.haberapp

import adapter.EditorAdapter
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dto.Editors
import network.RetrofitClient
import network.Service
import retrofit2.Call
import retrofit2.Response

@Suppress("RedundantOverride")
class EditorFragment:Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ):View? {
        val view= inflater.inflate(R.layout.editor_fragments,container,false)
        val editorRecyclerView=view.findViewById<RecyclerView>(R.id.e_recyclerView)
        editorRecyclerView.layoutManager=LinearLayoutManager(context)
        editorRecyclerView.hasFixedSize()
        val service=RetrofitClient.serviceBuilder().create(Service::class.java)
        service.getAuthors().enqueue(object :retrofit2.Callback<List<Editors>>
        {
            override fun onFailure(call: Call<List<Editors>>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
            override fun onResponse(call: Call<List<Editors>>, response: Response<List<Editors>>) {
                editorRecyclerView.adapter=context?.let { EditorAdapter(it,response.body()!!) }
            }
        })
         activity!!.setTitle("Editörler")
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }
    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.yazar_listele,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
            if(item!!.itemId==R.id.yazarList)
            {
                    val intent_to = Intent(activity, yazarlarActivity::class.java)
                    startActivity(intent_to)
                    return true
            }
        return false
    }







}