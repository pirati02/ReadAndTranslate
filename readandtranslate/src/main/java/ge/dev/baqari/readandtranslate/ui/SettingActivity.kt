package ge.dev.baqari.readandtranslate.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.view.View
import ge.dev.baqari.si.R
import ge.dev.baqari.readandtranslate.service.ClipboardService
import ge.dev.baqari.readandtranslate.serviceHelper.Companions.Companion.SYSTEM_ALERT_INTENT_REQUEST_CODE
import ge.dev.baqari.readandtranslate.serviceHelper.IOverDrawProvider
import ge.dev.baqari.readandtranslate.serviceHelper.OverDrawProvider
import ge.dev.baqari.readandtranslate.serviceHelper.ServiceCommangs.Companion.START_CLIPBOARD_MANAGER

class SettingActivity : AppCompatActivity() {

    private var overDrawProvider: IOverDrawProvider? = null
    private var serviceIntent: Intent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        if (serviceIntent == null)
            serviceIntent = Intent(this, ClipboardService::class.java)

        overDrawProvider = OverDrawProvider(this)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SYSTEM_ALERT_INTENT_REQUEST_CODE)
            if (overDrawProvider?.isOverMarshmallow()!!)
                if (!Settings.canDrawOverlays(this))
                    startClipboardService()
    }

    private fun startClipboardService() {
        serviceIntent?.action = START_CLIPBOARD_MANAGER
        startService(serviceIntent!!)
    }

    fun runService(view: View?) {
        if (overDrawProvider?.access(SYSTEM_ALERT_INTENT_REQUEST_CODE)!!)
            startClipboardService()
    }

    fun stopService(view: View) {
        stopService(intent)
    }
}
