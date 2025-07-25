package com.example.android_permission_manager.permissionmanager

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.content.pm.PackageManager
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

class StoragePermissionHelper {

    companion object {
        private const val REQUEST_STORAGE_PERMISSION = 2001

        @JvmStatic
        fun checkAndRequestPermission(activity: Activity, onGranted: () -> Unit) {
            if (hasStoragePermission(activity)) {
                onGranted()
            } else {
                val permissions = getRequiredPermissions()
                ActivityCompat.requestPermissions(activity, permissions, REQUEST_STORAGE_PERMISSION)
            }
        }

        @JvmStatic
        fun checkAndRequestPermission(fragment: Fragment, onGranted: () -> Unit) {
            val context = fragment.requireContext()
            if (hasStoragePermission(context)) {
                onGranted()
            } else {
                val permissions = getRequiredPermissions()
                fragment.requestPermissions(permissions, REQUEST_STORAGE_PERMISSION)
            }
        }

        @JvmStatic
        fun handlePermissionResult(activity: Activity, requestCode: Int, grantResults: IntArray, onGranted: () -> Unit) {
            if (requestCode == REQUEST_STORAGE_PERMISSION) {
                if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                    onGranted()
                } else {
                    showPermissionDeniedDialog(activity)
                }
            }
        }

        @JvmStatic
        fun handlePermissionResult(fragment: Fragment, requestCode: Int, grantResults: IntArray, onGranted: () -> Unit) {
            if (requestCode == REQUEST_STORAGE_PERMISSION) {
                if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                    onGranted()
                } else {
                    showPermissionDeniedDialog(fragment.requireActivity())
                }
            }
        }

        private fun getRequiredPermissions(): Array<String> {
            return when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> arrayOf(
                    Manifest.permission.READ_MEDIA_IMAGES,
                    Manifest.permission.READ_MEDIA_VIDEO,
                    Manifest.permission.READ_MEDIA_AUDIO
                )
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.R -> arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE
                    // MANAGE_EXTERNAL_STORAGE requires special intent, not requested here
                )
                else -> arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            }
        }

        private fun hasStoragePermission(context: android.content.Context): Boolean {
            return getRequiredPermissions().all {
                ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
            }
        }

        private fun showPermissionDeniedDialog(activity: Activity) {
            AlertDialog.Builder(activity)
                .setTitle("Storage Permission Required")
                .setMessage("Storage access is required to read and save files. Please allow it from settings.")
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
