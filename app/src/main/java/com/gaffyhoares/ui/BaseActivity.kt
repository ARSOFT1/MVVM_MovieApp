package com.gaffyhoares.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.gaffyhoares.R


abstract class BaseActivity : AppCompatActivity() {


    protected fun setBackArrowEnabled(toolbar: Toolbar, title: String, backArrow: Boolean?) {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(backArrow!!)
        supportActionBar!!.title = title
//        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_chevron_left)
    }
}