package io.github.gufeczek.crochet.convention.dependency

import io.github.gufeczek.crochet.convention.findLibrary
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyHandler

internal fun DependencyHandler.configureCompose(
    project: Project
) = with (project) {
    add("implementation", platform(findLibrary("androidx-compose-bom")))
    add("implementation", findLibrary("androidx-ui"))
    add("implementation", findLibrary("androidx-ui-graphics"))
    add("implementation", findLibrary("androidx-ui-tooling-preview"))
    add("implementation", findLibrary("androidx-material3"))
}