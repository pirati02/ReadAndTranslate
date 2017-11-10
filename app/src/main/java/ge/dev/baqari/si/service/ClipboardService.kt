package ge.dev.baqari.si.service

import android.app.Service
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.os.IBinder
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.WindowManager
import ge.dev.baqari.si.App
import ge.dev.baqari.si.serviceHelper.ServiceCommangs.Companion.START_CLIPBOARD_MANAGER
import ge.dev.baqari.si.serviceHelper.ServiceCommangs.Companion.STOP_CLIPBOARD_MANAGER
import ge.dev.baqari.si.ui.TranslateOverlay
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ClipboardService : Service(), ClipboardManager.OnPrimaryClipChangedListener {

    private var clipBoardManager: ClipboardManager? = null
    private var translateOverlay: TranslateOverlay? = null
    private var windowManager: WindowManager? = null

    override fun onCreate() {
        super.onCreate()
        if (clipBoardManager == null)
            clipBoardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?

        if (windowManager == null)
            windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager

        val displayMetrics = DisplayMetrics()
        windowManager!!.defaultDisplay.getMetrics(displayMetrics)


        val params = WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_TOAST,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT)
        params.gravity = Gravity.TOP or Gravity.LEFT
        params.x = 10
        params.y = 2

        if (translateOverlay == null)
            translateOverlay = TranslateOverlay(this, params, windowManager!!)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            START_CLIPBOARD_MANAGER -> {
                clipBoardManager?.addPrimaryClipChangedListener(this)
            }
            STOP_CLIPBOARD_MANAGER -> {
                clipBoardManager?.removePrimaryClipChangedListener(this)
                stopSelf()
            }
        }
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onPrimaryClipChanged() {
        val primaryClip = clipBoardManager?.primaryClip?.getItemAt(0)?.text
        translate(primaryClip.toString())
    }

    private fun translate(text: String) {
        (applicationContext as App).
                apiClient?.
                transalteApi?.
                translate(text)?.
                observeOn(AndroidSchedulers.mainThread())?.
                subscribeOn(Schedulers.io())?.
                subscribe({
                    val result = it.rows?.map { i ->
                        i.value?.text
                    }
                    translateOverlay?.show(text, result)
                }, {
                    it.printStackTrace()
                    translateOverlay?.hide()
                })
    }
}
