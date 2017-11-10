package ge.dev.baqari.readandtranslate

import android.app.Application
import ge.dev.baqari.readandtranslate.api.TranslateApiClient


class App : Application() {
    var apiClient: TranslateApiClient? = null

    override fun onCreate() {
        super.onCreate()
        apiClient = TranslateApiClient()
    }
}