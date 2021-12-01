package com.example.orenburjie.Utils

import com.example.orenburjie.MainActivity

object StringUtils {

    fun getString(resId: Int) = MainActivity.instance?.resources?.getString(resId)

}