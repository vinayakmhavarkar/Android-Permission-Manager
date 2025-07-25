package com.example.android_permission_manager.permissionmanager


import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

class CameraPermissionHelper {

    companion object {

        private const val REQUEST_CAMERA_PERMISSION = 1001

        @JvmStatic
        fun checkAndRequestPermission(activity: Activity, onGranted: Runnable, onDenied: Runnable? = null) {
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                onGranted.run()
            } else {
                ActivityCompat.requestPermissions(
                    activity,
                    arrayOf(Manifest.permission.CAMERA),
                    REQUEST_CAMERA_PERMISSION
                )
                onDenied?.run()
            }
        }

        @JvmStatic
        fun checkAndRequestPermission(fragment: Fragment, onGranted: Runnable, onDenied: Runnable? = null) {
            if (ContextCompat.checkSelfPermission(fragment.requireContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                onGranted.run()
            } else {
                fragment.requestPermissions(
                    arrayOf(Manifest.permission.CAMERA),
                    REQUEST_CAMERA_PERMISSION
                )
                onDenied?.run()
            }
        }

        @JvmStatic
        fun handlePermissionResult(activity: Activity, requestCode: Int, grantResults: IntArray, onGranted: Runnable, onDenied: Runnable? = null) {
            if (requestCode == REQUEST_CAMERA_PERMISSION) {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    onGranted.run()
                } else {
                    onDenied?.run()
                    showPermissionDeniedDialog(activity)
                }
            }
        }

        @JvmStatic
        fun handlePermissionResult(fragment: Fragment, requestCode: Int, grantResults: IntArray, onGranted: Runnable, onDenied: Runnable? = null) {
            if (requestCode == REQUEST_CAMERA_PERMISSION) {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    onGranted.run()
                } else {
                    onDenied?.run()
                    showPermissionDeniedDialog(fragment.requireActivity())
                }
            }
        }

        private fun showPermissionDeniedDialog(activity: Activity) {
            AlertDialog.Builder(activity)
                .setTitle("Camera Permission Required")
                .setMessage("Camera access is required to take photos. Please allow it.")
                .setPositiveButton("Go to Settings") { _, _ ->
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    intent.data = Uri.fromParts("package", activity.packageName, null)
                    activity.startActivity(intent)
                }
                .setNegativeButton("Cancel", null)
                .show()
        }
    }
}
