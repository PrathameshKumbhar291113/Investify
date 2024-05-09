package com.prathameshkumbhar.investify

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class InvestifyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

    }

}