package com.sunit.news

import com.sunit.news.datastore.models.UserData

sealed interface MainActivityUIState {
    data object Loading : MainActivityUIState
    data class Success(val userData: UserData) : MainActivityUIState
}
