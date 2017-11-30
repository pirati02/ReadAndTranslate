# ReadAndTranslate
<img src="https://github.com/pirati02/ReadAndTranslate/blob/master/readandtranslate/src/main/res/drawable/readandtranslat.gif"/>

[![](https://jitpack.io/v/pirati02/ReadAndTranslate.svg)](https://jitpack.io/#pirati02/ReadAndTranslate)

application should override this Application class and BaseApp interface (example):
    
    class DemoApplication : BaseApp, Application() {
        override lateinit var apiClient: TranslateApiClient

        override fun onCreate() {
            super.onCreate()
            apiClient = TranslateApiClient()
        }
    }

activity example is here:
https://github.com/pirati02/ReadAndTranslate/blob/master/app/src/main/java/ge/dev/baqari/si/SettingActivity.kt
