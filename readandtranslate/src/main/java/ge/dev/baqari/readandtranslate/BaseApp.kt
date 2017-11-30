package ge.dev.baqari.readandtranslate

import ge.dev.baqari.readandtranslate.api.TranslateApiClient

interface BaseApp {
    var apiClient: TranslateApiClient
}