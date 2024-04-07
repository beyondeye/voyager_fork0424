import org.jetbrains.compose.compose

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
    id("com.vanniktech.maven.publish")
    id("kotlinx-atomicfu")
}

setupModuleForComposeMultiplatform(fullyMultiplatform=true)
android {
    namespace = "com.beyondeye.kbloc.compose"
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.kblocCore)
                implementation(libs.coroutines.core)
                implementation(libs.kotlinx.collections.immutable)
                compileOnly(compose.runtime)
                compileOnly(compose.runtimeSaveable)
//                implementation(Deps.AtomicFu.common)
                //implementation(Deps.Napier.core)
            }
        }

        val jvmTest by getting {
            dependencies {
                //              implementation(libs.junit.api)
                //             runtimeOnly(libs.junit.engine)
            }
        }

//        val androidTest by getting {
//            dependencies {
//                implementation(compose.runtime)
//                implementation(compose.ui)
////                testImplementation("junit:junit:${Versions.junit_version}")
////                testImplementation ("org.assertj:assertj-core:${Versions.assertj_version}")
////                androidTestImplementation("androidx.test.ext:junit:1.1.3")
////                androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
//            }
//        }

        val androidMain by getting {
            dependencies {
//                implementation("androidx.core:core-ktx:${Versions.androidx_corektx_version}")
                compileOnly(libs.compose.activity)
                api(libs.lifecycle.viewModelCompose)
                implementation(libs.lifecycle.runtime)
                // https://developer.android.com/jetpack/androidx/releases/lifecycle#version_26_2
                //implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.0-alpha01")
            }
        }
        val commonWebMain by getting {
            dependencies {
               //  implementation(libs.multiplatformUuid)
            }
        }
        val jsMain by getting {
        }
        val jsTest by getting {
        }
    }
}
