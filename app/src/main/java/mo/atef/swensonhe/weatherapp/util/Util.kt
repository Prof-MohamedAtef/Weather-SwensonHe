package mo.atef.swensonhe.weatherapp.util

import android.view.View
import mo.atef.swensonhe.weatherapp.view.MainActivity
import java.text.SimpleDateFormat


class Util {
    companion object{
        fun getDateTime(date:String):String{
            val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm")
            return formatter.format(parser.parse(date))
        }

        fun applyImmersiveMode(hasFocus: Boolean, decorView: View){
            if (hasFocus) {
                val decorView: View = decorView
                decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                )
            }
        }

        val CITY: String="Cairo"
        var MainActivity: MainActivity? = null
        const val MULTIPART = "multipart/form-data"

    }



}