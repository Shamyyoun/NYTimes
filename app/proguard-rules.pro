# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-keep class android.support.v8.renderscript.** { *; }
-keep class androidx.renderscript.** { *; }

# Uncomment for DexGuard only
#-keepresourcexmlelements manifest/application/meta-data@value=GlideModule
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable
-keep public class * extends java.lang.Exception

-keepclassmembers class android.arch.** { *; }
-keep class android.arch.** { *; }
-dontwarn android.arch.**

# Rules for IconTextInputLayout
-keep class com.google.android.material.textfield.TextInputLayout { *; }
-keep class com.google.android.material.internal.CollapsingTextHelper { *; }


-keepclassmembernames class kotlinx.** {
    volatile <fields>;
}

-keepnames class * implements android.os.Parcelable
-keepnames class * implements java.io.Serializable
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

-keep class androidx.appcompat.widget.** { *; }

-keep,includedescriptorclasses class com.vella.common.**$$serializer { *; } # <-- change package name to your app's
-keepclassmembers class com.vella.common.** { # <-- change package name to your app's
    *** Companion;
}