package com.frogobox.basemusic.source

import com.frogobox.basemusic.base.data.BaseDataSource
import com.frogobox.basemusic.model.Favorite

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * BaseMusicPlayer
 * Copyright (C) 18/08/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.basemusicplayer.source
 *
 */
interface FrogoDataSource : BaseDataSource {

    // Room Database -------------------------------------------------------------------------------

    fun saveRoomFavorite(data: Favorite) : Boolean

    // Get
    fun getRoomFavorite(callback: GetRoomDataCallBack<List<Favorite>>)

    // Update
    fun updateRoomFavorite(tableId: Int, title: String, description: String, dateTime: String) : Boolean

    // Search
    fun searchRoomFavorite(scriptId: String, callback: GetRoomDataCallBack<List<Favorite>>)

    // Delete
    fun deleteRoomFavorite(tableId: Int) : Boolean

    // Nuke
    fun nukeRoomFavorite() : Boolean
    // ---------------------------------------------------------------------------------------------

    // Interface Helper ---------------------------------------------------------------------------
    // Save
    interface SaveRoomDataCallBack<T>: BaseDataSource.ResponseCallback<T>
    interface SavePrefCallBack<T>: BaseDataSource.ResponseCallback<T>

    // Get
    interface GetRoomDataCallBack<T> : BaseDataSource.ResponseCallback<T>

    // Update
    interface UpdateRoomDataCallBack<T> : BaseDataSource.ResponseCallback<T>

    // Delete
    interface DeleteRoomDataCallBack<T> : BaseDataSource.ResponseCallback<T>
    // ---------------------------------------------------------------------------------------------

}