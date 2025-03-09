package io.github.gufeczek.crochet.convention.dependency

import io.github.gufeczek.crochet.convention.findLibrary
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyHandler

internal fun DependencyHandler.configureRoom(
    project: Project
) = with (project) {
    add("implementation", findLibrary("room-runtime"))
    add("implementation", findLibrary("room-ktx"))
    add("ksp", findLibrary("room-compiler"))
}