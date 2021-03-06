package gb.kotlin_course_home_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import gb.kotlin_course_home_1.databinding.ActivityMainBinding
import gb.kotlin_course_home_1.view.details.WeatherListFragment

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

    }

}