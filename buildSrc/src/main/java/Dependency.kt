object Versions {
    const val kotlin_version = "1.4.32"
    const val kotlin_test_version = "3.1.11"
    const val mockito_version = "3.0.0"
    const val firebase_messaging_version = "21.0.1"
    const val support_test_version = "1.3.0"
    const val support_library = "1.2.0"
    const val support_annotations = "1.1.0"
    const val buildToolsVersion = "30.0.2"
    const val multiDexVersion = "2.0.0"
    const val kotlinMockito = "2.2.0"
    const val location_services_version = "18.0.0"
    const val espresso_idling_resources = "3.3.0"
    const val archLifecycleVersion ="2.3.1"
    const val securityCryptoVersion = "1.1.0-alpha03"
}

object Libs {
    const val support_annotations = "androidx.annotation:annotation:${Versions.support_annotations}"
    const val support_appcompat = "androidx.appcompat:appcompat:${Versions.support_library}"
    const val firebase_messaging = "com.google.firebase:firebase-messaging:${Versions.firebase_messaging_version}"
    const val espresso_idling_resources = "androidx.test.espresso:espresso-idling-resource:${Versions.espresso_idling_resources}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin_version}"
    const val location_services = "com.google.android.gms:play-services-location:${Versions.location_services_version}"
    const val lifecycle_extensions = "androidx.lifecycle:lifecycle-process:${Versions.archLifecycleVersion}"
    const val lifecycle_compiler =  "android.arch.lifecycle:compiler:${Versions.archLifecycleVersion}"
    const val security_crypto = "androidx.security:security-crypto:${Versions.securityCryptoVersion}"
}

object TestLibs {
    const val kotlin_test = "io.kotlintest:kotlintest-runner-junit4:${Versions.kotlin_test_version}"
    const val mockito_android = "org.mockito:mockito-android:${Versions.mockito_version}"
    const val kotlin_mockito = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.kotlinMockito}"
    const val support_test_runner = "androidx.test:runner:${Versions.support_test_version}"
    const val support_test_rules = "androidx.test:rules:${Versions.support_test_version}"
    const val multiDex = "androidx.multidex:multidex-instrumentation:${Versions.multiDexVersion}"
}