package mo.atef.swensonhe.weatherapp.util

import android.os.Build
import android.view.View
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import mo.atef.swensonhe.weatherapp.view.MainActivity
import java.sql.Time
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class Util {
    companion object{

        fun getTime():String{
            val c = Calendar.getInstance()
            val hour = c.get(Calendar.HOUR_OF_DAY)
            val minute = c.get(Calendar.MINUTE)
            return hour.toString()+" : "+minute.toString()
        }

        fun getHour():String{
             val c = Calendar.getInstance()
            return c.get(Calendar.HOUR_OF_DAY).toString()
        }

        fun getDateTime():String{
            val simpleDate = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            return simpleDate.format(Date()).toString()
        }

//        fun epochToHour(epoch:String):String{
//
//            return
//        }

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