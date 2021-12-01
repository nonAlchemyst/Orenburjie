package com.example.orenburjie

import android.text.TextUtils.replace
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.example.orenburjie.fragments.*
import com.example.orenburjie.interfaces.OnBackPressed

class Router {

    companion object{

        private val instance = Router()
        private var onSystemBackPressedListener: OnBackPressed? = null

        fun getInstance() = instance

        fun setSystemBackPressedListener(listener: OnBackPressed){
            onSystemBackPressedListener = listener
        }
    }

    fun backToCultureFragment(){
        backTo(CultureFragment::class.java.name)
    }

    fun backToMainScreen(){
        backTo(MainScreenFragment::class.java.name)
    }

    fun backToMenuList(){
        if(backTo(CultureFragment::class.java.name)){
            return
        }
        if(backToFirst(ZapovednikiFragment::class.java.name, NatureFragment::class.java.name)){
            return
        }
        if(backTo(ExcursionFragment::class.java.name)){
            return
        }
    }

    fun showMapScreenFragment(){
        addFragment(MapScreenFragment())
    }

    fun showCultureDescriptionFragment(){
        addFragment(CultureDescriptionFragment())
    }

    fun showExcursionsInfoFragment(){
        addFragment(ExcursionsInfoFragment())
    }

    fun showExcursionsDescriptionFragment(){
        addFragment(ExcursionsDescriptionFragment())
    }

    fun showRestingPlacesFragment(){
        addFragment(RestingPlacesFragment())
    }

    fun showZapovednikiFragment(){
        addFragment(ZapovednikiFragment())
    }

    fun showDescriptionFragment(){
        addFragment(DescriptionScreenFragment())
    }

    fun showCultureFragment(){
        addFragment(CultureFragment())
    }

    fun showNatureFragment(){
        addFragment(NatureFragment())
    }

    fun showExcursionsFragment(){
        addFragment(ExcursionFragment())
    }

    fun showMainScreenFragment(){
        setFirstFragment(MainScreenFragment())
    }

    fun back(){
        val manager = MainActivity.instance?.supportFragmentManager
        if(manager?.fragments?.size == 1){
            //MainActivity.instance?.back()
            MainActivity.instance?.finish()
        }
        else{
            manager?.popBackStack()
        }
    }

    fun systemBack() {
        val fragment = MainActivity.instance?.supportFragmentManager?.findFragmentById(R.id.fragment_container)

        if (fragment != null) {
            back()
            //onSystemBackPressedListener?.onBackPressed()
        }
    }

    //Требуется javaClass.name
    private fun backTo(destinationClassName: String): Boolean{
        val manager = MainActivity.instance?.supportFragmentManager
        return manager?.popBackStackImmediate(destinationClassName, 0) ?: false
    }

    private fun backToFirst(firstDestinationClassName: String, secondDestinationClassName: String): Boolean{
        val manager = MainActivity.instance?.supportFragmentManager
        manager?.backStackEntryCount?.let{
            var i = it - 1
            while ( i >= 0){
                val entryName = manager.getBackStackEntryAt(i).name
                if(entryName == firstDestinationClassName){
                    return manager.popBackStackImmediate(firstDestinationClassName, 0)
                }
                if(entryName == secondDestinationClassName){
                    return manager.popBackStackImmediate(secondDestinationClassName, 0)
                }
                i--
            }
        } ?: return manager?.popBackStackImmediate(firstDestinationClassName, 0) ?: false
        return false
    }

    private fun setFirstFragment(fragment: Fragment) {
        val manager = MainActivity.instance?.supportFragmentManager

        manager?.popBackStack(fragment.javaClass.name, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        manager?.executePendingTransactions()

        manager?.commit {
            replace(R.id.fragment_container, fragment, fragment.javaClass.name)
            addToBackStack(fragment.javaClass.name)
        }
    }

    private fun addFragment(fragment: Fragment){
        val manager = MainActivity.instance?.supportFragmentManager

        manager?.commit {
            replace(R.id.fragment_container, fragment, fragment.javaClass.name)
            addToBackStack(fragment.javaClass.name)
        }
    }

}