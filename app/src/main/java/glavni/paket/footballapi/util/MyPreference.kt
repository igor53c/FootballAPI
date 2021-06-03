package glavni.paket.footballapi.util

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import glavni.paket.footballapi.util.Constants.SHARED_PREFERENCES_KEY_CLUB_ID
import glavni.paket.footballapi.util.Constants.SHARED_PREFERENCES_KEY_NAME
import glavni.paket.footballapi.util.Constants.SHARED_PREFERENCES_PREFIX
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MyPreference @Inject constructor(@ApplicationContext context : Context) {

    private val sharedPref = context.getSharedPreferences(SHARED_PREFERENCES_PREFIX, Context.MODE_PRIVATE)

    fun getName(): String? {
        return sharedPref.getString(SHARED_PREFERENCES_KEY_NAME, "")
    }
    fun setName(name: String?) {
        sharedPref.edit().putString(SHARED_PREFERENCES_KEY_NAME, name).apply()
    }
    fun getClubId(): Int {
        return sharedPref.getInt(SHARED_PREFERENCES_KEY_CLUB_ID, 0)
    }
    fun setClubId(id: Int) {
        sharedPref.edit().putInt(SHARED_PREFERENCES_KEY_CLUB_ID, id).apply()
    }
}