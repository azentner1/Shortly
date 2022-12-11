package com.urlshort.shortly

import android.app.Application
import com.urlshort.shortly.base.database.RealmDB
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {

    override fun onCreate() {
        super.onCreate()
        RealmDB.init(applicationContext)
    }
}