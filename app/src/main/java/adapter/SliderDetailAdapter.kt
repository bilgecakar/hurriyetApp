package adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.github.chrisbanes.photoview.PhotoView
import dto.Veri
import timefighter.raywenderlich.haberapp.R


class SliderDetailAdapter (private val context: Context, val veri:Veri) : PagerAdapter() {

    private var layoutInflater: LayoutInflater?=null

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view.equals(`object`)
    }
    override fun getCount(): Int {
        return  veri.Files.size
    }
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v = layoutInflater!!.inflate(R.layout.activity_slider_detail , null)
        val image = v.findViewById<PhotoView>(R.id.photoView) as ImageView
        Glide.with(context).load(veri.Files[position].FileUrl).into(image)
        container.addView(v)
        return v
    }
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}
