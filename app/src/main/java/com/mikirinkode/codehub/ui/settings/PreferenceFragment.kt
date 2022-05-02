package com.mikirinkode.codehub.ui.settings

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import androidx.preference.SwitchPreference
import com.mikirinkode.codehub.R

class PreferenceFragment : PreferenceFragmentCompat(),
    SharedPreferences.OnSharedPreferenceChangeListener {
    private lateinit var IS_DARK: String
    private lateinit var darkModePreference: SwitchPreference


    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
        init()
        setSummaries()
    }

    private fun init() {
        IS_DARK = resources.getString(R.string.key_dark_mode)

        darkModePreference = findPreference<SwitchPreference>(IS_DARK) as SwitchPreference
    }


    private fun setSummaries() {
        val preference = PreferenceManager.getDefaultSharedPreferences(requireContext())
        darkModePreference.isChecked = preference.getBoolean(IS_DARK, false)
    }

    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences?.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences?.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String?) {
        if (key == IS_DARK) {
            darkModePreference.isChecked = sharedPreferences.getBoolean(IS_DARK,false)
            updateTheme(darkModePreference.isChecked)
        }
    }

    private fun updateTheme(checked: Boolean) {
        if (checked) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }


    companion object {
        private const val DEFAULT_VALUE = "Not Set"
    }
}