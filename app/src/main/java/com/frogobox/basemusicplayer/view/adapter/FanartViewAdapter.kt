package com.frogobox.basemusicplayer.view.adapter

import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.frogobox.basemusicplayer.base.view.BaseViewAdapter
import com.frogobox.basemusicplayer.base.view.BaseViewHolder
import kotlinx.android.synthetic.main.item_grid_fanart.view.*

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * BaseMusicPlayer
 * Copyright (C) 19/12/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.basemusicplayer.view.adapter
 *
 */
class FanartViewAdapter : BaseViewAdapter<String>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<String> {
        return FashionViewHolder(viewLayout(parent))
    }

    inner class FashionViewHolder(view: View) : BaseViewHolder<String>(view) {
        private val iv_image = view.iv_poster
        override fun initComponent(data: String) {
            super.initComponent(data)
            Glide.with(itemView.context).load(data).into(iv_image)
        }

    }

}