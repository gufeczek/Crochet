package io.github.gufeczek.crochet

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import io.github.gufeczek.crochet.ui.CrochetApp
import io.github.gufeczek.crochet.ui.rememberCrochetAppState
import io.github.gufeczek.crochet.ui.theme.CrochetTheme

const val PERMISSIONS_REQUEST_RECORD_AUDIO = 1
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        // TODO: move this to :common:speech-recogniton:adapter
        val permissionCheck =
            ContextCompat.checkSelfPermission(applicationContext, android.Manifest.permission.RECORD_AUDIO)
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.RECORD_AUDIO),
                PERMISSIONS_REQUEST_RECORD_AUDIO
            )
        }

        setContent {
            val appState = rememberCrochetAppState()

            CrochetTheme {
                CrochetApp(appState)
            }
        }
    }
}