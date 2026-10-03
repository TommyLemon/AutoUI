plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "zuo.biao.library"
    compileSdk = 36

    defaultConfig {
        minSdk = 29

        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.fastjson)
    implementation(libs.unitauto)
    implementation(libs.androidasync)
    implementation(libs.apijson)
    implementation(libs.okhttp)
    implementation(libs.glide)
    implementation(libs.refreshlayoutkernel)
    implementation(libs.refreshheaderclassics)
    implementation(libs.refreshfooterclassics)

    api(project(":UIGOX"))
    // api(project(":UIGOX-NOOP")) // 替换上一行，方便测试 NOOP 空跑模式
    // TODO 你的业务项目需要 删除 上面配置，取消注释 来启用 下面两行
    // debugApi(project(":UIGOX")) // 只有 DEBUG 包有录制回放功能
    // releaseApi(project(":UIGOX-NOOP")) // Release 包排除 UIGOX 相关代码逻辑来避免可能的安全隐患
}