# ========================================================
# ProGuard Rules File
# ========================================================
# This file contains rules for code shrinking, obfuscation, and optimization.
# It ensures that critical classes and methods are preserved during the build process.

# ========================================================
# Room Database
# ========================================================
# Preserve all Room database classes and their members to ensure proper functionality
-keep class androidx.room.** { *; }

# ========================================================
# SQLCipher Encryption
# ========================================================
# Preserve all SQLCipher classes and their members to ensure encryption functionality
-keep class net.sqlcipher.** { *; }

# ========================================================
# BuildConfig
# ========================================================
# Preserve the BuildConfig class to ensure access to build-time constants.
-keep class novalogics.android.mvibase.BuildConfig { *; }

# ========================================================
# Additional Security Rules
# ========================================================
# Preserve all classes and methods annotated with @Keep to prevent accidental removal
-keep class * {
    @androidx.annotation.Keep *;
}

# Preserve all classes and methods in the application package to prevent obfuscation
-keep class novalogics.android.mvibase.** { *; }

# Preserve all View classes and their methods to ensure proper UI functionality
-keep class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(***);
    public *** get*();
}

# Preserve all Parcelable classes and their CREATOR fields to ensure proper serialization
-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}

# Preserve all enum classes and their values to prevent obfuscation
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Preserve all native methods to ensure proper JNI functionality
-keepclasseswithmembernames class * {
    native <methods>;
}

# Preserve all resource classes to ensure proper access to resources
-keepclassmembers class **.R$* {
    public static <fields>;
}

# ========================================================
# Debugging and Logging
# ========================================================
# Preserve logging methods to ensure debug logs are not removed
-keepclassmembers class * {
    public static void log*(...);
    public static void d*(...);
    public static void e*(...);
    public static void i*(...);
    public static void v*(...);
    public static void w*(...);
}

# ========================================================
# Third-Party Libraries
# ========================================================
# Add rules for third-party libraries here to ensure their functionality is preserved
# Example: Retrofit
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes *Annotation*

# Example: Gson
-keep class com.google.gson.** { *; }
-keep class com.google.gson.stream.** { *; }
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

# ========================================================
# End of ProGuard Rules
# ========================================================

