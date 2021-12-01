package com.example.orenburjie.Utils

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import com.example.orenburjie.MainActivity

object ColorUtils {

    private const val defaultErrorColor = Color.BLACK

    @ColorInt
    fun getColor(@ColorRes colorId: Int) = MainActivity.instance?.getColor(colorId) ?: defaultErrorColor

    fun getColorFromAttribute(attrId: Int): Int{
        val attrs = intArrayOf(attrId)
        val typedArray = MainActivity.instance?.applicationContext?.obtainStyledAttributes(attrs)
        val color = typedArray?.getResourceId(0, android.R.color.black) ?: defaultErrorColor
        typedArray?.recycle()
        return color
    }

    fun getColorStateListFromAttribute(attrId: Int) = ColorStateList.valueOf(getColorFromAttribute(attrId))

    fun getColorStateListFromColor(colorId: Int) = ColorStateList.valueOf(getColor(colorId))

}