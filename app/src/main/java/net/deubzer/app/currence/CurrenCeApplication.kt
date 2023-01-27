package net.deubzer.app.currence

import android.app.Application
import net.deubzer.app.currence.di.AppContainer
import net.deubzer.app.currence.di.AppContainerImpl

class CurrenCeApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainerImpl(this)
    }

    fun getCurrenCeContainer() : AppContainer {
        return container
    }
}