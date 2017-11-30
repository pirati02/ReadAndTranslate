package ge.dev.baqari.si

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import ge.dev.baqari.readandtranslate.service.ClipboardService
import ge.dev.baqari.readandtranslate.serviceHelper.Companions
import ge.dev.baqari.readandtranslate.serviceHelper.IOverDrawProvider
import ge.dev.baqari.readandtranslate.serviceHelper.OverDrawProvider
import ge.dev.baqari.readandtranslate.serviceHelper.ServiceCommangs

class SettingActivity : android.support.v7.app.AppCompatActivity() {

    private var overDrawProvider: IOverDrawProvider? = null
    private var serviceIntent: Intent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        if (serviceIntent == null)
            serviceIntent = Intent(this, ClipboardService::class.java)
        makePermission()
        overDrawProvider = OverDrawProvider(this)
    }

    private fun makePermission() {
        if (android.support.v4.content.ContextCompat.checkSelfPermission(this, Manifest.permission.SYSTEM_ALERT_WINDOW) == PackageManager.PERMISSION_DENIED)
            if (android.support.v4.app.ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SYSTEM_ALERT_WINDOW)) {
                android.support.v4.app.ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SYSTEM_ALERT_WINDOW), Companions.RUNTIME_REQUEST_CODE)
            } else {
                android.support.v4.app.ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SYSTEM_ALERT_WINDOW), Companions.RUNTIME_REQUEST_CODE)
            }
    }

    @android.support.annotation.RequiresApi(Build.VERSION_CODES.M)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Companions.SYSTEM_ALERT_INTENT_REQUEST_CODE){

        }
            if (overDrawProvider?.isOverMarshmallow()!!)
                if (!Settings.canDrawOverlays(this))
                    startClipboardService()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == Companions.RUNTIME_REQUEST_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            startClipboardService()
    }

    private fun startClipboardService() {
        serviceIntent?.action = ServiceCommangs.START_CLIPBOARD_MANAGER
        startService(serviceIntent!!)
    }

    private fun stopClipboardService() {
        serviceIntent?.action = ServiceCommangs.STOP_CLIPBOARD_MANAGER
        startService(serviceIntent!!)
    }

    fun runService(view: View?) {
        if (overDrawProvider?.access(Companions.SYSTEM_ALERT_INTENT_REQUEST_CODE)!!)
            startClipboardService()
    }

    fun stopService(view: View) {
        stopClipboardService()
    }
}