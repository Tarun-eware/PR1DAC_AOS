// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.androidLibrary) apply false
    id("org.cyclonedx.bom") version "1.7.1"
}
group = "com.purred.pr1digitalad"
version = "0.0.1"


tasks.register<org.cyclonedx.gradle.CycloneDxTask>("generateSbom") {
    group = "reporting"
    description = "Generates a CycloneDX SBOM"
    outputFormat.set("json")
    includeConfigs.set(
        project.configurations
            .matching {
                it.name in listOf(
                    "runtimeClasspath",
                    "compileClasspath",
                    "releaseRuntimeClasspath",
                    "debugRuntimeClasspath"
                )
            }
            .map { it.name }
    )
}
