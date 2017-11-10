package ge.dev.baqari.readandtranslate.serviceHelper

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.support.annotation.RequiresApi

class OverDrawProvider(reference: Context) : IOverDrawProvider {
    private var context: Context = reference

    private var requestCode: Int? = null

    override fun access(requestCode: Int): Boolean {
        this.requestCode = requestCode
        return if (isOverMarshmallow()) {
            if (!Settings.canDrawOverlays(context)) {
                permissionRequest()
                false
            } else true
        } else true
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun permissionRequest() {
        val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + context?.packageName))
        (context as Activity).startActivityForResult(intent, requestCode!!)
    }

    override fun isOverMarshmallow(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
}