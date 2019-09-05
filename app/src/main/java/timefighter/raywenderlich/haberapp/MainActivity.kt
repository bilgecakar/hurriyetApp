package timefighter.raywenderlich.haberapp

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {




    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val newsFragment= NewsFragment()
        openFragment(newsFragment)
        val navMenu= findViewById<BottomNavigationView>(R.id.bottomNavigation)
        navMenu.setOnNavigationItemSelectedListener (object : BottomNavigationView.OnNavigationItemSelectedListener
        {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when(item.itemId){
                    R.id.navigation_news->
                    {
                        val newsFragment= NewsFragment()
                        openFragment(newsFragment)
                        return true
                    }
                    R.id.navigation_editor->
                    {
                        val editorFragment=EditorFragment()
                        openFragment(editorFragment)
                        return true
                    }
                }
                return false
            }
        })
    }
    fun openFragment(fragment: Fragment)
    {
        val transaction : FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.Container,fragment)
        transaction.commit()
    }

}
