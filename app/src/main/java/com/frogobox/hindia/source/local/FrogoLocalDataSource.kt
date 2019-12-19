package com.frogobox.hindia.source.local

import android.content.SharedPreferences
import androidx.annotation.VisibleForTesting
import com.frogobox.hindia.base.view.BaseCallback
import com.frogobox.hindia.model.Favorite
import com.frogobox.hindia.model.Fashion
import com.frogobox.hindia.source.FrogoDataSource
import com.frogobox.hindia.source.dao.FavoriteDao
import com.frogobox.hindia.source.dao.FashionDao
import com.frogobox.hindia.util.AppExecutors
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * PublicSpeakingBooster
 * Copyright (C) 16/08/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.publicspeakingbooster.source.local
 *
 */
class FrogoLocalDataSource private constructor(
    private val appExecutors: AppExecutors,
    private val sharedPreferences: SharedPreferences,
    private val fashionDao: FashionDao,
    private val favoriteDao: FavoriteDao
) : FrogoDataSource {
    override fun saveRoomFavorite(data: Favorite): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getRoomData(callback: FrogoDataSource.GetRoomDataCallBack<List<Fashion>>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getRoomFavorite(callback: FrogoDataSource.GetRoomDataCallBack<List<Favorite>>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateRoomFavorite(
        tableId: Int,
        title: String,
        description: String,
        dateTime: String
    ): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun searchRoomFavorite(
        scriptId: String,
        callback: FrogoDataSource.GetRoomDataCallBack<List<Favorite>>
    ) {
        appExecutors.diskIO.execute {
            favoriteDao.searchData(scriptId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : BaseCallback<List<Favorite>>() {
                    override fun onCallbackSucces(data: List<Favorite>) {
                        callback.onShowProgressDialog()
                        callback.onSuccess(data)
                        if (data.size == 0) {
                            callback.onEmpty()
                        }
                        callback.onHideProgressDialog()

                    }

                    override fun onCallbackError(code: Int, errorMessage: String) {
                        callback.onFailed(code, errorMessage)
                    }

                    override fun onAddSubscribe(disposable: Disposable) {
                        addSubscribe(disposable = disposable)
                    }

                    override fun onCompleted() {
                        callback.onHideProgressDialog()
                    }
                })
        }
    }

    override fun deleteRoomFavorite(tableId: Int): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun nukeRoomFavorite(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    private
    var compositeDisposable: CompositeDisposable? = null

    fun addSubscribe(disposable: Disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = CompositeDisposable()

            compositeDisposable?.add(disposable)
        }
    }

    /**
     * Clear all subscribers active in app
     */
    private fun clearSubscribe() {
        if (compositeDisposable != null) {
            compositeDisposable?.clear()
        }
    }

    companion object {

        private var INSTANCE: FrogoLocalDataSource? = null

        @JvmStatic
        fun getInstance(
            appExecutors: AppExecutors,
            sharedPreferences: SharedPreferences,
            fashionDao: FashionDao,
            favoriteDao: FavoriteDao

        ): FrogoLocalDataSource {
            if (INSTANCE == null) {
                synchronized(FrogoLocalDataSource::javaClass) {
                    INSTANCE = FrogoLocalDataSource(
                        appExecutors,
                        sharedPreferences,
                        fashionDao,
                        favoriteDao
                    )
                }
            }
            return INSTANCE!!
        }

        @VisibleForTesting
        fun clearInstance() {
            INSTANCE = null
        }
    }

}
