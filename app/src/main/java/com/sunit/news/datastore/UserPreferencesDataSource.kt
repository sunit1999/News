package com.sunit.news.datastore

import androidx.datastore.core.DataStore
import com.sunit.news.DarkThemeConfigProto
import com.sunit.news.UserPreferences
import com.sunit.news.copy
import com.sunit.news.datastore.models.DarkThemeConfig
import com.sunit.news.datastore.models.UserData
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class UserPreferencesDataSource @Inject constructor(
    private val userPreferencesDataStore: DataStore<UserPreferences>
) {
    val userData = userPreferencesDataStore.data
        .catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                Timber.e("Error reading user preferences.", exception)
                emit(UserPreferences.getDefaultInstance())
            } else {
                throw exception
            }
        }
        .map {
            UserData(
                darkThemeConfig = when (it.darkThemeConfig) {
                    DarkThemeConfigProto.DARK_THEME_CONFIG_LIGHT -> DarkThemeConfig.LIGHT
                    DarkThemeConfigProto.DARK_THEME_CONFIG_DARK -> DarkThemeConfig.DARK
                    else -> DarkThemeConfig.SYSTEM_DEFAULT
                }
            )
        }

    suspend fun updateDarkThemeConfig(darkThemeConfig: DarkThemeConfig) {
        try {
            userPreferencesDataStore.updateData {
                it.copy {
                    this.darkThemeConfig = when (darkThemeConfig) {
                        DarkThemeConfig.SYSTEM_DEFAULT -> DarkThemeConfigProto.DARK_THEME_CONFIG_SYSTEM_DEFAULT
                        DarkThemeConfig.DARK -> DarkThemeConfigProto.DARK_THEME_CONFIG_DARK
                        DarkThemeConfig.LIGHT -> DarkThemeConfigProto.DARK_THEME_CONFIG_LIGHT
                    }
                }
            }
        } catch (ioException: IOException) {
            Timber.e("Failed to update user preferences. $ioException")
        }
    }

}
