package io.github.gufeczek.crochet.convention.dependency

import io.github.gufeczek.crochet.convention.findLibrary
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyHandler

internal fun DependencyHandler.configureKoin(
    project: Project
) = with (project) {
    add("implementation", platform(findLibrary("koin-bom")))
    add("implementation", findLibrary("koin-core"))
    add("implementation", findLibrary("koin-android"))
    add("implementation", findLibrary("koin-compose"))
    add("implementation", findLibrary("koin-compose-viewmodel"))
    add("implementation", findLibrary("koin-coroutines"))
    add("implementation", findLibrary("koin-annotations"))
    add("ksp", findLibrary("koin-ksp-compiler"))
}