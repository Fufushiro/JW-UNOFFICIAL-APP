package ia.ankherth.jwunofficial

import android.content.Context
import android.content.SharedPreferences

object LanguagePreferences {
    private const val PREFS_NAME = "language_prefs"
    private const val KEY_LANGUAGE_CODE = "language_code"
    private const val KEY_LANGUAGE_NAME = "language_name"
    private const val KEY_FIRST_LAUNCH = "first_launch"

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun isFirstLaunch(context: Context): Boolean {
        return getPreferences(context).getBoolean(KEY_FIRST_LAUNCH, true)
    }

    fun setFirstLaunchComplete(context: Context) {
        getPreferences(context).edit().putBoolean(KEY_FIRST_LAUNCH, false).apply()
    }

    fun saveLanguage(context: Context, languageCode: String, languageName: String) {
        getPreferences(context).edit().apply {
            putString(KEY_LANGUAGE_CODE, languageCode)
            putString(KEY_LANGUAGE_NAME, languageName)
            apply()
        }
    }

    fun getLanguageCode(context: Context): String? {
        return getPreferences(context).getString(KEY_LANGUAGE_CODE, null)
    }

    fun getLanguageName(context: Context): String? {
        return getPreferences(context).getString(KEY_LANGUAGE_NAME, null)
    }

    fun clearLanguage(context: Context) {
        getPreferences(context).edit().clear().apply()
    }
}

