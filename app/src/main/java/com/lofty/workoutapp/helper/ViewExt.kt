package com.lofty.workoutapp.helper

import android.view.View


fun View.hide() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun setOnClickListeners(views: List<View>,onClick:() -> Unit){
    views.forEach {
        it.setOnClickListener {
            onClick()
        }
    }
}