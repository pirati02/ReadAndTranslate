package ge.dev.baqari.si.ui

import android.content.Context
import android.support.v7.widget.AppCompatImageButton
import android.support.v7.widget.AppCompatTextView
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.ListView
import ge.dev.baqari.si.R

class TranslateOverlay(context: Context, params: WindowManager.LayoutParams, window: WindowManager) {
    private var rootView: LinearLayout? = null
    private var translatedList: ListView? = null
    private var textToTranslate: AppCompatTextView? = null
    private var closeButton: AppCompatImageButton? = null
    private var mWindowManager: WindowManager? = null
    private var mWindowParams: WindowManager.LayoutParams? = null

    init {
        mWindowManager = window
        mWindowParams = params
        rootView = LayoutInflater.from(context).inflate(R.layout.translate_overlay, null) as LinearLayout
        textToTranslate = rootView?.findViewById(R.id.text_to_translate)
        translatedList = rootView?.findViewById(R.id.translated_text_list)!!
        closeButton = rootView?.findViewById(R.id.translate_close_button)

        closeButton?.setOnClickListener{
            hide()
        }
    }

    fun show(current: String, translated: List<String?>?) {
        textToTranslate?.text = current
        translatedList?.adapter = TranslationAdapter(translated)
        mWindowManager?.addView(rootView, mWindowParams)
    }

    fun hide() {
        textToTranslate?.text = null
        translatedList?.adapter = null

        mWindowManager?.removeView(rootView)
    }
}