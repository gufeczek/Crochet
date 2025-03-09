package io.github.gufeczek.crochet.convention.dependency

import io.github.gufeczek.crochet.convention.findLibrary
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyHandler

internal fun DependencyHandler.configureKtor(
    project: Project
) = with (project) {
    add("implementation", findLibrary("ktor-client-core"))
    add("implementation", findLibrary("ktor-client-cio"))
    add("implementation", findLibrary("ktor-client-logging"))
}