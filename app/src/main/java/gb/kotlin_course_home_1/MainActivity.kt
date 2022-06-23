package gb.kotlin_course_home_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val info = InfoTraining("Урок 1) Введение в Kotlin", "21.06.2022")
        val buttonDate: AppCompatButton = findViewById(R.id.btn_example_date)
        val buttonTitle: AppCompatButton = findViewById(R.id.btn_example_title)

        buttonTitle.setOnClickListener {
            Toast.makeText(this, info.title, Toast.LENGTH_SHORT).show()
        }
        buttonDate.setOnClickListener {
            Toast.makeText(this, info.date, Toast.LENGTH_SHORT).show()
        }




    }

    data class InfoTraining(val title: String, val date: String)

}