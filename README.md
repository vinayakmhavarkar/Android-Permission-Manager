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


### CAMRA 
# 📸 Camera Permission Usage
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




MIT License




