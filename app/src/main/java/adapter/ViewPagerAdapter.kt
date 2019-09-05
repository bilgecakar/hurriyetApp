package adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import dto.Veri
import timefighter.raywenderlich.haberapp.R
import timefighter.raywenderlich.haberapp.SliderDetail

class ViewPagerAdapter(private val context: Context, val veriList: List<Veri>) : PagerAdapter() {

     private var layoutInflater:LayoutInflater?=null

      override fun isViewFromObject(view: View, `object`: Any): Boolean {
          return view.equals(`object`)
      }
      override fun getCount(): Int {
          return  10 //veriList.count()
      }
      override fun instantiateItem(container: ViewGroup, position: Int): Any {

          layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
          val v = layoutInflater!!.inflate(R.layout.news_fragment_item , null)
          val image = v.findViewById<View>(R.id.imageview) as ImageView
          val text = v.findViewById<TextView>(R.id.sliderTitle)
          text.text=veriList[position].Title
          Glide.with(context).load(veriList[position].Files[0].FileUrl).into(image)
          container.addView(v)

          image.setOnClickListener(object : View.OnClickListener
          {
              override fun onClick(p0: View?) {
                  val intent = Intent(context,SliderDetail::class.java)
                  intent.putExtra("id",veriList[position].Id)
                  context.startActivity(intent)
              }
          })
          return v
      }

      override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
          container.removeView(`object` as View)
      }

}