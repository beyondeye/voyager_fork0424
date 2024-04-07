plugins {
    kotlin("multiplatform")
//    kotlin("native.cocoapods")
    id("com.android.library")
    id("org.jetbrains.compose")
    id("com.vanniktech.maven.publish")
    id("kotlinx-atomicfu")
}

setupModuleForComposeMultiplatform(fullyMultiplatform=true)

android {
    namespace = "com.beyondeye.kbloc"
}

kotlin {
//    iosX64()
//    iosArm64()
//    iosSimulatorArm64()

//    cocoapods {
//        summary = "KBloc core module"
//        homepage = "Link to KBloc core homepage"
//        ios.deploymentTarget = "14.1"
//        framework {
//            baseName = "kbloc-core"
//        }
//    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.voyagerCore)
                compileOnly(compose.runtime)
                implementation(libs.coroutines.core)
                implementation(libs.kotlinx.collections.immutable)
//                implementation(Deps.AtomicFu.common)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                //https://github.com/mockk/mockk/releases
                implementation (libs.mockk.common)
                //see https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-test/index.html
                //sse https://developer.android.com/kotlin/coroutines/test#testdispatchers
                implementation(libs.coroutines.test)
                implementation(libs.kotlinx.datetime)
            }
        }
        val androidMain by getting
        //val androidTest by getting {
        //    dependencies {
        //        implementation("io.mockk:mockk:1.12.4")
        //    }
        //}
//        val iosX64Main by getting
//        val iosArm64Main by getting
//        val iosSimulatorArm64Main by getting
//        val iosMain by creating {
//            dependsOn(commonMain)
//            iosX64Main.dependsOn(this)
//            iosArm64Main.dependsOn(this)
//            iosSimulatorArm64Main.dependsOn(this)
//        }
//        val iosX64Test by getting
//        val iosArm64Test by getting
//        val iosSimulatorArm64Test by getting
//        val iosTest by creating {
//            dependsOn(commonTest)
//            iosX64Test.dependsOn(this)
//            iosArm64Test.dependsOn(this)
//            iosSimulatorArm64Test.dependsOn(this)
//        }
        val jvmTest by getting {
            dependencies {
//                implementation(libs.junit.api)
//                runtimeOnly(libs.junit.engine)
            }
        }

        val jsMain by getting {
        }
        val jsTest by getting {
        }

        val commonWebMain by getting {
            dependencies {
                implementation(libs.multiplatformUuid)
            }
        }
    }
}

//android {
//    compileSdk = 32
//    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
//    defaultConfig {
//        minSdk = 21
//        targetSdk = 32
//    }
//}
