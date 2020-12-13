package com.whiterabbit.app.base

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.whiterabbit.app.R

open class BaseActivity: AppCompatActivity() {

    fun addFragment(fragment: Fragment, tag: String, isAddToBackSTack: Boolean){
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.container, fragment,tag)
        if(isAddToBackSTack)
            ft.addToBackStack(tag)
        ft.commit()
    }
}