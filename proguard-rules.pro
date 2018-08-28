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

-keep, includedescriptorclasses class android.arch.lifecycle.MutableLiveData {
    public protected *;
}
-keep, includedescriptorclasses class android.arch.lifecycle.ViewModel {
    public protected *;
}
-keep, includedescriptorclasses class android.arch.lifecycle.ViewModelProvider {
    public protected *;
}
-keep, includedescriptorclasses class android.arch.lifecycle.ViewModelProviders {
    public protected *;
}
-keep, includedescriptorclasses class android.support.constraint.ConstraintLayout {
    public protected *;
}
-keep, includedescriptorclasses class android.support.v4.app.FragmentActivity {
    public protected *;
}
-keep, includedescriptorclasses class android.support.v4.content.ContextCompat {
    public protected *;
}
-keep, includedescriptorclasses class android.support.v7.app.AppCompatActivity {
    public protected *;
}
-keep, includedescriptorclasses class android.support.v7.widget.RecyclerView {
    public protected *;
}
-keep, includedescriptorclasses class android.support.v7.widget.RecyclerView$Adapter {
    public protected *;
}
-keep, includedescriptorclasses class android.support.v7.widget.RecyclerView$OnScrollListener {
    public protected *;
}
-keep, includedescriptorclasses class android.support.v7.widget.RecyclerView$ViewHolder {
    public protected *;
}
-keep, includedescriptorclasses class com.bumptech.glide.Glide {
    public protected *;
}
-keep, includedescriptorclasses class com.bumptech.glide.RequestBuilder {
    public protected *;
}
-keep, includedescriptorclasses class com.bumptech.glide.RequestManager {
    public protected *;
}
-keep, includedescriptorclasses class com.example.android.instantappsample.base.ApiInterface {
    public protected *;
}
-keep, includedescriptorclasses class com.example.android.instantappsample.base.MyObserver {
    public protected *;
}
-keep, includedescriptorclasses class com.example.android.instantappsample.base.RestClient {
    public protected *;
}
-keep, includedescriptorclasses class com.example.android.instantappsample.base.data.Character {
    public protected *;
}
-keep, includedescriptorclasses class com.example.android.instantappsample.base.data.CharacterPage {
    public protected *;
}
-keep, includedescriptorclasses class com.example.android.instantappsample.base.data.Info {
    public protected *;
}
-keep, includedescriptorclasses class com.google.android.gms.auth.api.credentials.Credential {
    public protected *;
}
-keep, includedescriptorclasses class com.google.android.gms.auth.api.credentials.CredentialPickerConfig$Builder {
    public protected *;
}
-keep, includedescriptorclasses class com.google.android.gms.auth.api.credentials.CredentialRequest$Builder {
    public protected *;
}
-keep, includedescriptorclasses class com.google.android.gms.auth.api.credentials.Credentials {
    public protected *;
}
-keep, includedescriptorclasses class com.google.android.gms.auth.api.credentials.CredentialsClient {
    public protected *;
}
-keep, includedescriptorclasses class com.google.android.gms.auth.api.credentials.HintRequest$Builder {
    public protected *;
}
-keep, includedescriptorclasses class com.google.android.gms.instantapps.InstantApps {
    public protected *;
}
-keep, includedescriptorclasses class com.google.android.gms.instantapps.PackageManagerCompat {
    public protected *;
}
-keep, includedescriptorclasses class com.google.android.gms.tasks.Task {
    public protected *;
}
-keep,includedescriptorclasses class com.google.android.gms.tasks.OnCompleteListener {
 public protected *;
}
-keep, includedescriptorclasses class io.reactivex.Observable {
    public protected *;
}
-keep, includedescriptorclasses class io.reactivex.schedulers.Schedulers {
    public protected *;
}
-keep, includedescriptorclasses class kotlin.Lazy {
    public protected *;
}
-keep, includedescriptorclasses class kotlin.LazyKt {
    public protected *;
}
-keep, includedescriptorclasses class kotlin.TypeCastException {
    public protected *;
}
-keep, includedescriptorclasses class kotlin.Unit {
    public protected *;
}
-keep, includedescriptorclasses class kotlin.collections.CollectionsKt {
    public protected *;
}
-keep, includedescriptorclasses class kotlin.jvm.functions.Function0 {
    public protected *;
}
-keep, includedescriptorclasses class kotlin.jvm.functions.Function1 {
    public protected *;
}
-keep, includedescriptorclasses class kotlin.jvm.internal.Intrinsics {
    public protected *;
}
-keep, includedescriptorclasses class kotlin.jvm.internal.Lambda {
    public protected *;
}
-keep, includedescriptorclasses class kotlin.jvm.internal.PropertyReference1Impl {
    public protected *;
}
-keep, includedescriptorclasses class kotlin.jvm.internal.Reflection {
    public protected *;
}
-keep, includedescriptorclasses class timber.log.Timber {
    public protected *;
}
-keep, includedescriptorclasses class timber.log.Timber$DebugTree {
    public protected *;
}