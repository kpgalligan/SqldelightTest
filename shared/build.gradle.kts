plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("app.cash.sqldelight") version "2.0.0-rc02"
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                //put your multiplatform dependencies here
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("app.cash.sqldelight:android-driver:2.0.0-rc02")
            }
        }
        val nativeMain by getting {
            dependencies {
                implementation("app.cash.sqldelight:native-driver:2.0.0-rc02")
            }
        }
    }
}

android {
    namespace = "co.touchlab.testing.sqlapp"
    compileSdk = 33
    defaultConfig {
        minSdk = 31
    }
}

sqldelight {
    databases {
        create("Database") {
            packageName.set("co.touchlab.testing")
        }
    }
}