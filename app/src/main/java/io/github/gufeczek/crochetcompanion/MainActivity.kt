package io.github.gufeczek.crochetcompanion

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import io.github.gufeczek.crochetcompanion.ui.CrochetApp
import io.github.gufeczek.crochetcompanion.ui.rememberCrochetAppState
import io.github.gufeczek.crochetcompanion.ui.theme.CrochetCompanionTheme
import org.koin.android.ext.android.inject

const val PERMISSIONS_REQUEST_RECORD_AUDIO = 1
class MainActivity : ComponentActivity() {
    //private val speechManager: SpeechManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        val permissionCheck =
            ContextCompat.checkSelfPermission(applicationContext, android.Manifest.permission.RECORD_AUDIO)
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.RECORD_AUDIO),
                PERMISSIONS_REQUEST_RECORD_AUDIO
            )
        }
        //speechManager.initialize()
        setContent {
            val appState = rememberCrochetAppState()

            CrochetCompanionTheme {
                CrochetApp(appState)
            }
        }
    }
}