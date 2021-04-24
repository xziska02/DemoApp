package com.peter.ziska.demoapp.flows.data.persistance

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.peter.ziska.demoapp.flows.data.persistance.model.RemoteKeys

@Dao
interface RemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(remoteKey: RemoteKeys)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(remoteKeys: List<RemoteKeys>)

    @Query("SELECT * FROM remote_keys WHERE articleKey = :id")
    suspend fun getRemoteKey(id: String): RemoteKeys?

    @Query("DELETE FROM remote_keys WHERE articleKey = :articleId")
    suspend fun deleteByArticleId(articleId: String)

    @Query("DELETE FROM remote_keys")
    suspend fun clear()
}