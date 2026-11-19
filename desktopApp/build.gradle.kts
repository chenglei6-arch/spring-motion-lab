import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    jvmToolchain(21)
}

dependencies {
    implementation(project(":simulation-core"))
    implementation(compose.desktop.currentOs)
    implementation(libs.compose.material3)
    implementation(libs.kotlinx.coroutinesSwing)
}

compose.desktop {
    application {
        mainClass = "io.github.chenglei6arch.springmotionlab.desktop.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "spring-motion-lab"
            packageVersion = "1.0.0"
        }
    }
}
