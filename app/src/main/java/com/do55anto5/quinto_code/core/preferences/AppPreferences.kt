package com.do55anto5.quinto_code.core.preferences

import android.content.Context
import com.do55anto5.quinto_code.core.constants.SharedPreferencesKeys.FILE_NAME
import com.do55anto5.quinto_code.core.constants.SharedPreferencesKeys.WELCOME_VISITED

class AppPreferences(context: Context) {

    private val sharedPrefs = context.getSharedPreferences(
        FILE_NAME,
        Context.MODE_PRIVATE
    )

    fun saveWelcomeVisited(visited: Boolean) {
        sharedPrefs.edit().putBoolean(WELCOME_VISITED, visited).apply()
    }

    fun getWelcomeVisited(): Boolean {
        return sharedPrefs.getBoolean(WELCOME_VISITED, false)
    }


}