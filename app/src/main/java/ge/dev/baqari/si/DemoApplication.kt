package ge.dev.baqari.si

import android.app.Application
import ge.dev.baqari.readandtranslate.BaseApp
import ge.dev.baqari.readandtranslate.api.TranslateApiClient


class DemoApplication : BaseApp, Application() {
    override lateinit var apiClient: TranslateApiClient

    override fun onCreate() {
        super.onCreate()
        apiClient = TranslateApiClient()
    }
}