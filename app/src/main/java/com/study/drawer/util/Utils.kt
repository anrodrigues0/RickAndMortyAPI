package com.study.drawer.util

import android.view.View
import android.widget.ProgressBar

class Utils {

    fun getIdFromUrl(urls: List<String>) : List<String> {
        val ids:MutableList<String> = ArrayList();
        for (url in urls){
            val id = url.substringAfterLast("/");
            ids.add(id)
        }
        return ids
    }


    fun handleProgressbar(show:Boolean, progress:ProgressBar) {
        if(show) {
            progress.visibility = View.VISIBLE
        }else {
            progress.visibility = View.GONE
        }
    }
}