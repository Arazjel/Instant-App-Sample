-keepattributes *Annotation*,Signature

-keepattributes SourceFile,LineNumberTable
-keep public class * extends java.lang.Exception

# Retain service method parameters when optimizing.
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}

# Project
-dontnote com.example.android.instantappsample.**
# Android
-dontnote android.**
-dontnote com.android.**
# Retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}
-dontnote retrofit2.**
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
# OK
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**
-dontnote okhttp3.**
-dontnote okio.**
# RxJava
-dontnote io.reactivex.**
# Timber
-dontnote timber.**
# Experimental
-dontwarn **Lambda**
# ViewModel LiveData
-keepclassmembers class * extends android.arch.lifecycle.ViewModel {
    <init>(...);
}
-keepclassmembers class * {
    @android.arch.lifecycle.OnLifecycleEvent *;
}
-keepclassmembers class android.arch.** { *; }
-keep class android.arch.** { *; }
-dontwarn android.arch.**
