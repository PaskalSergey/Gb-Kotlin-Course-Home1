package gb.kotlin_course_home_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val info = InfoTraining("Урок 1) Введение в Kotlin", "21.06.2022")
        val objectTraining = info.copy()
        var tapCounter = 0
//        val buttonDate: AppCompatButton = findViewById(R.id.btn_example_date)
//        val buttonTitle: AppCompatButton = findViewById(R.id.btn_example_title)
//        //val textViewExample: TextView = findViewById(R.id.text_view_fun_example)
//        val buttonCycles: AppCompatButton = findViewById(R.id.btn_example_cycles)

        fun cyclesTraining(counter: Int) {
            val range = 1..10
            val numbers: ArrayList<Int> = arrayListOf()

            // Цикл for
            if (counter % 2 == 0) {
                for (i in range step 2) {
                    numbers.add(i)
                    //textViewExample.text = numbers.toString()
                }
            } else {
                // Цикл While
                var count = 10
                while (--count > 0) {
                    numbers.add(count / 5)
               //     textViewExample.text = numbers.toString()
                }
            }
        }

//        buttonTitle.setOnClickListener {
//            Toast.makeText(this, info.title, Toast.LENGTH_SHORT).show()
//        }
//        buttonDate.setOnClickListener {
//            Toast.makeText(this, objectTraining.date, Toast.LENGTH_SHORT).show()
//        }
//        buttonCycles.setOnClickListener {
//            cyclesTraining(tapCounter)
//            if (tapCounter % 2 == 0) {
//                Toast.makeText(this, "Выполняю цикл for", Toast.LENGTH_SHORT).show()
//            } else {
//                Toast.makeText(this, "Выполняю цикл while", Toast.LENGTH_SHORT).show()
//            }
//            tapCounter++
//        }


    }

    data class InfoTraining(val title: String, val date: String)


}