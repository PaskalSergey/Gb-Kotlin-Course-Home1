package gb.kotlin_course_home_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button : AppCompatButton = findViewById(R.id.btn_example)
        button.setOnClickListener{
            Toast.makeText(this, "Зафиксировал нажатие на кнопку", Toast.LENGTH_SHORT).show()
        }
    }
}