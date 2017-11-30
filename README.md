# ReadAndTranslate
<img src="https://github.com/pirati02/ReadAndTranslate/blob/master/readandtranslate/src/main/res/drawable/readandtranslat.gif"/>

[![](https://jitpack.io/v/pirati02/ReadAndTranslate.svg)](https://jitpack.io/#pirati02/ReadAndTranslate)

Step 1. 

    allprojects {
            repositories {
                ...
                maven { url 'https://jitpack.io' }
            }
        }
Step 2. Add the dependency

	dependencies {
		compile 'com.github.pirati02:ReadAndTranslate:1.1.2'
	}
    
application should override this Application class and BaseApp interface (example):
    
    class DemoApplication : BaseApp, Application() {
        override lateinit var apiClient: TranslateApiClient

        override fun onCreate() {
            super.onCreate()
            apiClient = TranslateApiClient()
        }
    }
 
also service in manifest: 
    
    <service
            android:name="ge.dev.baqari.readandtranslate.service.ClipboardService"
            android:enabled="true"
            android:exported="true" />

activity example is here:
https://github.com/pirati02/ReadAndTranslate/blob/master/app/src/main/java/ge/dev/baqari/si/SettingActivity.kt
