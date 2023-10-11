package com.sunit.news.data

import com.sunit.news.datastore.models.DarkThemeConfig
import com.sunit.news.datastore.models.UserData
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {
    val userData: Flow<UserData>
    suspend fun updateDarkThemeConfig(darkThemeConfig: DarkThemeConfig)
    suspend fun updateCountryCode(countryCode: String)
}
