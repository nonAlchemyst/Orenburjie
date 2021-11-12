package com.example.orenburjie.viewmodels

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.databinding.ObservableField
import com.example.orenburjie.ColorUtils
import com.example.orenburjie.R


class NavigationTab {

    private var onPageClickListener: OnSelect? = null
    private val selectedColor = ColorStateList.valueOf(R.attr.secondaryBackgroundColor)
    private val deselectedColor = ColorStateList.valueOf(Color.TRANSPARENT)
    val leftPageColor = ObservableField<ColorStateList>(deselectedColor)
    val rightPageColor = ObservableField<ColorStateList>(deselectedColor)

    fun setListener(listener: OnSelect){
        onPageClickListener = listener
    }

    fun selectLeft(){
        leftPageColor.set(selectedColor)
        rightPageColor.set(deselectedColor)
        onPageClickListener?.onLeftSelected()
    }

    fun selectRight() {
        rightPageColor.set(selectedColor)
        leftPageColor.set(deselectedColor)
        onPageClickListener?.onRightSelected()
    }

    abstract class OnSelect{
        open fun onLeftSelected(){}
        open fun onRightSelected(){}
    }

}