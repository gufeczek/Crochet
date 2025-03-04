package io.github.gufeczek.crochet

import android.app.Application
import io.github.gufeczek.feature.counter.FeatureModule
import io.github.gufeczek.crochet.core.speech.SpeechModule
import io.github.gufeczek.crochet.data.DataModule
import io.github.gufeczek.crochet.speech.recogniton.adapter.SpeechProviderModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

class CrochetApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@CrochetApplication)

            modules(
                AppModule().module,
                DataModule().module,
                FeatureModule().module,
                SpeechModule().module,
                SpeechProviderModule().module
            )
        }
    }
}