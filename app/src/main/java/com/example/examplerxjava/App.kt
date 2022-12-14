package com.example.examplerxjava

import android.app.Application
import android.util.Log
import io.reactivex.rxjava3.exceptions.UndeliverableException
import io.reactivex.rxjava3.plugins.RxJavaPlugins

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        RxJavaPlugins.setErrorHandler { e ->
            if (e is UndeliverableException) {
                // Merely log undeliverable exceptions
                Log.e("Error", "${e.message}")
            } else {
                // Forward all others to current thread's uncaught exception handler
                Thread.currentThread().also { thread ->
                    thread.uncaughtExceptionHandler.uncaughtException(thread, e)
                }
            }
        }
    }
}