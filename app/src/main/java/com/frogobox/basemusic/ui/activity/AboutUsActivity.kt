package com.frogobox.basemusic.ui.activity

import android.os.Bundle
import com.frogobox.basemusic.R
import com.frogobox.basemusic.base.admob.BaseAdmobActivity

class AboutUsActivity : BaseAdmobActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)
        setupDetailActivity("")
    }
}
