package gb.kotlin_course_home_1

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import gb.kotlin_course_home_1.databinding.ActivityMainBinding
import gb.kotlin_course_home_1.view.details.WeatherListFragment
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
                R.id.container, WeatherListFragment.newInstance()
            ).commit()
        }

        startService(Intent(this, MyService::class.java).apply {
            putExtra(BUNDLE_KEY, "hello")
        })

        val receiver = MyBroadcastReceiver()
        registerReceiver(receiver, IntentFilter("android.permission.CONNECTIVITY_ACTION"))



    }

}