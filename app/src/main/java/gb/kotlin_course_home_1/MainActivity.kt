package gb.kotlin_course_home_1

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import gb.kotlin_course_home_1.databinding.ActivityMainBinding
import gb.kotlin_course_home_1.view.contentprovider.ContentProviderFragment
import gb.kotlin_course_home_1.view.details.CitiesListFragment
import gb.kotlin_course_home_1.viewmodel.BUNDLE_KEY
import gb.kotlin_course_home_1.viewmodel.MyBroadcastReceiver
import gb.kotlin_course_home_1.viewmodel.MyService

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(
                R.id.container, CitiesListFragment.newInstance()
            ).commit()
        }

        startService(Intent(this, MyService::class.java).apply {
            putExtra(BUNDLE_KEY, "hello")
        })

        val receiver = MyBroadcastReceiver()
        registerReceiver(receiver, IntentFilter("android.permission.CONNECTIVITY_ACTION"))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_screen_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_content_provider -> {
                supportFragmentManager.apply {
                    beginTransaction().add(R.id.container, ContentProviderFragment())
                        .addToBackStack("").commitAllowingStateLoss()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}