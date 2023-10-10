package com.sunit.news.data.repository

import com.sunit.news.data.UserDataRepository
import com.sunit.news.datastore.UserPreferencesDataSource
import com.sunit.news.datastore.models.DarkThemeConfig
import javax.inject.Inject

class OfflineFirstUserDataRepository @Inject constructor(
    private val userPreferencesDataSource: UserPreferencesDataSource
) : UserDataRepository {
    override val userData = userPreferencesDataSource.userData

    override suspend fun updateDarkThemeConfig(darkThemeConfig: DarkThemeConfig) {
        userPreferencesDataSource.updateDarkThemeConfig(darkThemeConfig)
    }
}
