package ru.bilenkod.themesettingsexample

import android.content.Intent
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {

    private var isNightMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (checkAndSetNightMode()) {
            setTheme(R.style.DarkTheme)
        }
        setContentView(R.layout.activity_main)

        val switch: Switch = findViewById(R.id.switch_night_mode)
        switch.isChecked = isNightMode
        switch.setOnCheckedChangeListener { _: CompoundButton, nightModeOn: Boolean ->
            swapTheme(nightModeOn)
        }
    }

    private fun checkAndSetNightMode(): Boolean {
        var isNightModeCurrentlyOn = false
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            isNightModeCurrentlyOn = true
        }
        isNightMode = isNightModeCurrentlyOn
        return isNightModeCurrentlyOn
    }

    private fun swapTheme(nightModeOn: Boolean) {
        if (isNightMode != nightModeOn) {
            when (nightModeOn) {
                true -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                false -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            restartActivity()
        }
    }

    private fun restartActivity() {
        startActivity(Intent(applicationContext, this.javaClass))
        finish()
    }
}
