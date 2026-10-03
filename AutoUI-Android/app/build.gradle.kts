plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "uigox.demo"
    compileSdk = 36

    defaultConfig {
        applicationId = "uigox.demo"
        minSdk = 29
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        viewBinding = true
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    packaging {
        resources {
            excludes.add("**/application.properties")
        }
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.core.ktx)
    implementation(libs.fastjson)
    implementation(libs.androidasync)
    implementation(libs.unitauto)
    implementation(libs.apijson)
    implementation(libs.okhttp)
    implementation(libs.glide)
    implementation(libs.zxinglite)
    implementation(libs.refreshlayoutkernel)
    implementation(libs.refreshheaderclassics)
    implementation(libs.refreshfooterclassics)

    // 已在 ZBLibrary 里依赖，这里应该不需要重复依赖，如果编译报错找不到 UIGOX/UnitAuto 相关类，则可取消注释来显式依赖
    // debugApi(project(":UnitAuto-Apk")) // 只有 DEBUG 包有零代码单元测试功能
    // releaseApi(project(":UnitAuto-Apk-NOOP")) // Release 包排除 UnitAuto-Apk 相关代码逻辑来避免可能的安全隐患
    // debugApi(project(":UIGOX")) // 只有 DEBUG 包有录制回放功能
    // releaseApi(project(":UIGOX-NOOP")) // Release 包排除 UIGOX 相关代码逻辑来避免可能的安全隐患

    api(project(":ZBLibrary")) {
        exclude(group = "com.android.support")
    }

}