package gb.kotlin_course_home_1.viewmodel

import android.app.IntentService
import android.content.Intent

const val BUNDLE_KEY = "BUNDLE_KEY"
class MyService : IntentService("") {

    override fun onHandleIntent(intent: Intent?) {
        intent?.let {
            it.getStringExtra(BUNDLE_KEY)
            sendBroadcast(Intent().apply {
                action = "answer"
            })
        }
    }
}