package io.github.gufeczek.crochet.convention.dependency

import io.github.gufeczek.crochet.convention.findLibrary
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyHandler

internal fun DependencyHandler.configureAndroidCore(
    project: Project
) = with (project) {
    add("implementation", findLibrary("androidx-core-ktx"))
    add("implementation", findLibrary("androidx-lifecycle-runtime-ktx"))
}

