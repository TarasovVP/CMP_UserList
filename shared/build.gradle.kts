import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.sqlDelight)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    // Android
    androidTarget {
        tasks.withType<KotlinJvmCompile>().configureEach {
            compilerOptions {
                jvmTarget.set(JvmTarget.JVM_21)
            }
        }
    }
    // iOS
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            linkerOpts.add("-lsqlite3")
            baseName = "shared"
            isStatic = true
        }
    }
    // Desktop
    jvm {
        tasks.withType<KotlinJvmCompile>().configureEach {
            compilerOptions {
                jvmTarget.set(JvmTarget.JVM_21)
            }
        }
    }
    // Web
    js(IR) {
        browser {
            commonWebpackConfig {
                cssSupport {
                    enabled.set(true)
                }
            }
        }
        binaries.executable()
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.serialization)
            // Ktor
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            // SQLDelight
            implementation(libs.sqldelight.coroutines.extensions)
            // Koin
            implementation(libs.koin.core)
            implementation(libs.koin.compose.viewmodel)
            // Compose
            implementation(compose.runtime)
            implementation(compose.ui)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)
            // ViewModel
            implementation(libs.androidx.viewmodel.compose)
            // Coil
            implementation(libs.coil.compose)
            implementation(libs.coil.network.ktor)
        }
        androidMain.dependencies {
            // Ktor
            implementation(libs.ktor.client.android)
            // SQLDelight
            implementation(libs.sqldelight.android.driver)
            // Koin
            implementation(libs.koin.android)
        }
        iosMain.dependencies {
            // Ktor
            implementation(libs.ktor.client.darwin)
            // SQLDelight
            implementation(libs.sqldelight.native.driver)
        }
        jvmMain.dependencies {
            implementation(libs.kotlinx.coroutines.swing)
            // Ktor
            implementation(libs.ktor.client.java)
            // SQLDelight
            implementation(libs.sqldelight.java.driver)
        }
        jsMain.dependencies {
            // Ktor
            implementation(libs.ktor.client.js)
            // SQLDelight
            implementation(libs.web.worker.driver)
            implementation(npm("@cashapp/sqldelight-sqljs-worker", "2.0.2"))
            implementation(npm("sql.js", "1.6.2"))
            implementation(devNpm("copy-webpack-plugin", "9.1.0"))
        }
    }
}

android {
    namespace = "com.tarasovvp.cmpuserlist"
    compileSdk = 36
    defaultConfig {
        minSdk = 28
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

sqldelight {
    databases {
        create("UserDatabase") {
            packageName.set("com.tarasovvp.cmpuserlist.db")
            generateAsync.set(true)
            version = "1.0"
        }
    }
}
