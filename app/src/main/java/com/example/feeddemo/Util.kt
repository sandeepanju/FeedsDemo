package com.example.feeddemo

import android.app.Activity
import androidx.core.content.res.ResourcesCompat
import www.sanju.motiontoast.MotionToast

/*-----showing motion toast--------*/
fun ShowToast(activity: Activity, message: String, messageType: String){
    MotionToast.darkColorToast(activity,message,
        messageType,
        MotionToast.GRAVITY_BOTTOM,
        MotionToast.LONG_DURATION,
        ResourcesCompat.getFont(activity,R.font.helvetica_regular))
}