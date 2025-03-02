package io.github.gufeczek.crochetcompanion

import android.app.Application
import io.github.gufeczek.feature.counter.FeatureModule
import io.github.gufeczek.core.speech.SpeechModule
import io.github.gufeczek.data.DataModule
import io.github.gufeczek.crochet.speechprovider.SpeechProviderModule
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