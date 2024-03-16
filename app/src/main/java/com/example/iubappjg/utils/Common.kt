package com.example.iubappjg.utils

import android.content.Context
import android.widget.Toast
import java.time.Duration

class Common {

    companion object{

        fun showToast(context:Context, message:String, duration: Int=Toast.LENGTH_SHORT){
            Toast.makeText(context, message, duration).show()
        }
    }
}
