package com.study.drawer.view.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.study.drawer.R
import com.study.drawer.databinding.ResErrorBinding

class Error(context:Context,  attrs: AttributeSet):ConstraintLayout(context, attrs) {

    private val binding: ResErrorBinding = ResErrorBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.Error)
        setMsgError(attributes.getString(R.styleable.Error_msgError ) ?: "")
        setVisibility(attributes.getBoolean(R.styleable.Error_visible, false))
        attributes.recycle()
    }

    fun setMsgError(msg:String) {
        binding.txtError.text = msg
    }


    fun setMsgError(@StringRes msg:Int) {
        binding.txtError.setText(msg)
    }

    fun setVisibility(visibility: Boolean) {
        if(visibility) {
            binding.containerError.visibility = View.VISIBLE
        } else {
            binding.containerError.visibility = View.GONE
        }
    }

}