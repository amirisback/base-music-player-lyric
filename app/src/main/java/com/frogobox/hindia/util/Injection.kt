package com.frogobox.hindia.util

import android.content.Context
import androidx.preference.PreferenceManager
import com.frogobox.hindia.source.FrogoDataRepository
import com.frogobox.hindia.source.dao.FavoriteDao
import com.frogobox.hindia.source.dao.FashionDao
import com.frogobox.hindia.source.local.FrogoAppDatabase
import com.frogobox.hindia.source.local.FrogoLocalDataSource
import com.frogobox.hindia.source.remote.FrogoRemoteDataSource

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * BaseMusicPlayer
 * Copyright (C) 26/08/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.basemusicplayer.util
 *
 */
object Injection {

    fun provideFrogoRepository(context: Context): FrogoDataRepository {
        val fashionDao: FashionDao by lazy {
            FrogoAppDatabase.getInstance(context).fashionDao()
        }

        val favoriteDao: FavoriteDao by lazy {
            FrogoAppDatabase.getInstance(context).favoriteScriptDao()
        }

        val appExecutors = AppExecutors()

        return FrogoDataRepository.getInstance(FrogoRemoteDataSource(context),
            FrogoLocalDataSource.getInstance(
                appExecutors,
                PreferenceManager.getDefaultSharedPreferences(context),
                fashionDao,
                favoriteDao))
    }

}