package net.deubzer.app.currence

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CurrenCeApplication : Application() {

  override fun onCreate() {
    super.onCreate()
    if (BuildConfig.DEBUG) {
      Log.d("CURRENCE APPLICATION", "oncreate running and fin")
    }
  }
}
