package com.example.app.model.service

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserInfoManager(private val context: Context) {
    companion object {
        private val Context.userStore: DataStore<Preferences> by preferencesDataStore("user_store")
        val LOGGED = booleanPreferencesKey("LOGGED")
        val USERNAME = stringPreferencesKey("USERNAME")
    }

    val logged: Flow<Boolean> = context.userStore.data.map {
        it[LOGGED] ?: false
    }

    val userName: Flow<String> = context.userStore.data.map {
        it[USERNAME] ?: ""
    }

    suspend fun save(userName: String) {
        context.userStore.edit {
            it[LOGGED] = userName.isNotEmpty()
            it[USERNAME] = userName
        }
    }

    suspend fun clear() {
        context.userStore.edit {
            it[LOGGED] = false
            it[USERNAME] = ""
        }
    }

}