package io.github.gufeczek.crochet.core.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging

val client = HttpClient(CIO) {
    install(Logging) {
        logger = Logger.ANDROID
        level = LogLevel.HEADERS
    }
}