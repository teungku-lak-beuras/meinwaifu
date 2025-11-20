# HOLY FUCK
# https://drjansari.medium.com/mastering-proguard-in-android-multi-module-projects-agp-8-4-r8-and-consumable-rules-ae28074b6f1f
-keep class retrofit2.** { *; }
-keep interface retrofit2.Call
-keepattributes Signature, RuntimeVisibleAnnotations
-keep class * {
    @com.google.gson.annotations.SerializedName <fields>;
}
