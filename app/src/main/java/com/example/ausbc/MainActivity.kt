package com.example.ausbc

import android.Manifest.permission.CAMERA
import android.Manifest.permission.RECORD_AUDIO
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.hardware.usb.UsbDevice
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.PermissionChecker
import androidx.fragment.app.Fragment
import com.example.ausbc.databinding.ActivityMainBinding
import com.jiangdg.ausbc.utils.ToastUtils
import com.jiangdg.ausbc.utils.Utils

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn.setOnClickListener {
            replaceDemoFragment(DemoFragment())
        }
    }

    private fun replaceDemoFragment(fragment: Fragment) {
/*        val hasCameraPermission = PermissionChecker.checkSelfPermission(this, CAMERA)
        val hasStoragePermission =
            PermissionChecker.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE)
        if (hasCameraPermission != PermissionChecker.PERMISSION_GRANTED || hasStoragePermission != PermissionChecker.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, CAMERA)) {
                ToastUtils.show(R.string.permission_tip)
            }
            ActivityCompat.requestPermissions(
                this,
                arrayOf(CAMERA, WRITE_EXTERNAL_STORAGE, RECORD_AUDIO),
                REQUEST_CAMERA
            )
            return
        }*/
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, fragment)
        transaction.commitAllowingStateLoss()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CAMERA -> {
                val hasCameraPermission = PermissionChecker.checkSelfPermission(this, CAMERA)
                if (hasCameraPermission == PermissionChecker.PERMISSION_DENIED) {
                    ToastUtils.show(R.string.permission_tip)
                    return
                }
//                replaceDemoFragment(DemoMultiCameraFragment())
                replaceDemoFragment(DemoFragment())
//                replaceDemoFragment(GlSurfaceFragment())
            }
            REQUEST_STORAGE -> {
                val hasCameraPermission =
                    PermissionChecker.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE)
                if (hasCameraPermission == PermissionChecker.PERMISSION_DENIED) {
                    ToastUtils.show(R.string.permission_tip)
                    return
                }
                // todo

            }
            else -> {
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    companion object {
        private const val REQUEST_CAMERA = 0
        private const val REQUEST_STORAGE = 1
    }
}