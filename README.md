# Android Permission Manager

📱 A lightweight and easy-to-use Android library to manage runtime permissions with minimal code.

✅ Supports:
- Camera Permission
- Fragment & Activity compatibility
- Android versions from Lollipop (API 21) to Android 15+
- Custom Alert Dialog when permission is denied
- Java & Kotlin support

---

## 🔧 Setup

1. **Add the library to your project:**

### Step 1: Add JitPack to `settings.gradle` (if hosting on GitHub later)

#kotlin
```
dependencyResolutionManagement {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
### Step 2: Add dependency in app/build.gradle:
```
dependencies {
    implementation 'com.github.yourgithubusername:AndroidPermissionManager:1.0.0'
}
```



## 📸 Camera Permission Usage
- For Kotlin:
 ```
CameraPermissionHelper.requestCameraPermission(
    context = this,
    onGranted = {
        // Camera permission granted - continue your action
    },
    onDenied = {
        // Permission denied permanently or temporarily
    }
)
```
- For Java:
```
CameraPermissionHelper.INSTANCE.requestCameraPermission(
    this,
    () -> {
        // onGranted
    },
    () -> {
        // onDenied
    }
);
```

----

## 📂 Storage Permission Usage

> Works for accessing files from Downloads, Documents, SD Card, etc.

---

### 🛠 Required Permissions in `AndroidManifest.xml`

Add the following:

```xml
<!-- Required for accessing files in external storage -->
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"
    android:maxSdkVersion="28" />
```

### For Android 13+ (API 33+), add this instead:

```AndroidManifest.xml
<manifest>
    <!-- For Android 13 and above -->
    <uses-permission android:name="android.permission.READ_MEDIA_IMAGES" />
    <uses-permission android:name="android.permission.READ_MEDIA_VIDEO" />
    <uses-permission android:name="android.permission.READ_MEDIA_AUDIO" />
</manifest>
```

### ✅ Kotlin Usage
```
StoragePermissionHelper.requestStoragePermission(
    context = this,
    onGranted = {
        // Access files, download content, etc.
    },
    onDenied = {
        // Show a message or handle user denial
    }
)

```

### ✅ Java Usage
```
StoragePermissionHelper.INSTANCE.requestStoragePermission(
    this,
    () -> {
        // onGranted - Proceed with storage access
    },
    () -> {
        // onDenied - Show settings dialog or message
    }
);
```

### 🔐 Features
- ✅ Handles Android 6 (API 23) to Android 15+
- ✅ Compatible with scoped storage restrictions
- ✅ Shows customizable alert dialog if denied
- ✅ Automatically redirects user to app settings when "Don't ask again" is selected
- ✅ Supports both Activity and Fragment

### 📌 Notes
- For Android 11+ (API 30), you should use MANAGE_EXTERNAL_STORAGE only for special cases (e.g., full file system access apps).
- This library does not request MANAGE_EXTERNAL_STORAGE as it's discouraged for most Play Store apps.


MIT License




