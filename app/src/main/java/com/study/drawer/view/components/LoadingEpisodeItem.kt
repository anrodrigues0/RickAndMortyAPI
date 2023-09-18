package com.study.drawer.view.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.view.isVisible
import com.study.drawer.R
import com.study.drawer.databinding.ResLoadingEpisodeItemBinding

class LoadingEpisodeItem(context:Context, attr:AttributeSet):LinearLayout(context, attr) {

   private val binding = ResLoadingEpisodeItemBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        val typedArray = context.obtainStyledAttributes(attr,R.styleable.LoadingEpisodeItem)
        setVisible(typedArray.getBoolean(R.styleable.LoadingEpisodeItem_isLoading, false))
        typedArray.recycle()
    }

    fun setVisible(visible:Boolean) {
        binding.loadingEpisodeContainer.isVisible = visible
    }

}