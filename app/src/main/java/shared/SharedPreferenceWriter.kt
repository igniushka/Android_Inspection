package shared

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

const val MyPREFERENCES = "MyPrefs"

class SharedPreferenceWriter {
    private constructor() {}
    @SuppressLint("CommitPrefEdits")
    constructor(context: Context) {
        mPrefs = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE)
        prefEditor = mPrefs?.edit()
    }

    fun writeStringValue(key: String?, value: String?) {
        prefEditor!!.putString(key, value)
        prefEditor!!.commit()
    }

    fun writeIntValue(key: String?, value: Int) {
        prefEditor!!.putInt(key, value)
        prefEditor!!.commit()
    }

    fun setLoggedIn(key: String?, value: Boolean) {
        prefEditor!!.putBoolean(key, value)
        prefEditor!!.commit()
    }

    fun writeLongValue(key: String?, value: Long) {
        prefEditor!!.putLong(key, value)
        prefEditor!!.commit()
    }

    fun clearPreferenceValue(key: String?, value: String?) {
        prefEditor!!.putString(key, "")
        prefEditor!!.commit()
    }

    fun getString(key: String?): String? {
        return mPrefs!!.getString(key, "")
    }

    fun getInt(key: String?): Int {
        return mPrefs!!.getInt(key, 0)
    }

    fun getLoggedIn(key: String?): Boolean {
        return mPrefs!!.getBoolean(key, false)
    }

    fun getLong(key: String?): Long {
        return mPrefs!!.getLong(key, 0.0.toLong())
    }

    fun clearPreferenceValues() {
        prefEditor!!.clear()
        prefEditor!!.commit()
    }

    companion object {
        private var context: Context? = null
        private var sharePref: SharedPreferenceWriter? = null
        private var mPrefs: SharedPreferences? = null
        private var prefEditor: SharedPreferences.Editor? = null
        fun getInstance(c: Context?): SharedPreferenceWriter? {
            context = c
            if (null == sharePref) {
                sharePref = SharedPreferenceWriter()
                mPrefs = context!!.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE)
                prefEditor = mPrefs?.edit()
            }
            return sharePref
        }
    }
}