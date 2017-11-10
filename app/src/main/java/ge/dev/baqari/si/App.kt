package ge.dev.baqari.si

import android.app.Application
import ge.dev.baqari.si.api.TranslateApiClient


class App : Application() {
    var apiClient: TranslateApiClient? = null

    override fun onCreate() {
        super.onCreate()
        apiClient = TranslateApiClient()
    }
}