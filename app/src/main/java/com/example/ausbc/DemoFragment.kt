package com.example.ausbc

import android.hardware.usb.UsbDevice
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ausbc.databinding.FragmentDemoBinding
import com.jiangdg.ausbc.MultiCameraClient
import com.jiangdg.ausbc.base.CameraFragment
import com.jiangdg.ausbc.callback.ICameraStateCallBack
import com.jiangdg.ausbc.camera.bean.CameraRequest
import com.jiangdg.ausbc.render.env.RotateType
import com.jiangdg.ausbc.widget.AspectRatioTextureView
import com.jiangdg.ausbc.widget.IAspectRatio
import com.jiangdg.ausbc.render.RenderManager


class DemoFragment: CameraFragment() {
    private var mViewBinding: FragmentDemoBinding? = null


    override fun getRootView(inflater: LayoutInflater, container: ViewGroup?): View? {
        if (mViewBinding == null) {
            mViewBinding = FragmentDemoBinding.inflate(inflater, container, false)
        }
        return mViewBinding?.root
    }

    override fun getCameraView(): IAspectRatio? {
        return null
    }

    override fun getCameraViewContainer(): ViewGroup? {
        return mViewBinding?.cameraViewContainer
    }




    // camera open status callback
    override fun onCameraState(self: MultiCameraClient.ICamera,
                               code: ICameraStateCallBack.State,
                               msg: String?) {
        when (code) {
            ICameraStateCallBack.State.OPENED -> openCamera()
            ICameraStateCallBack.State.CLOSED -> closeCamera()
            ICameraStateCallBack.State.ERROR -> error("Error")
        }
    }



    override fun getCameraRequest(): CameraRequest {
        return CameraRequest.Builder()
            .setPreviewWidth(1280) // camera preview width
            .setPreviewHeight(720) // camera preview height
            .setRenderMode(CameraRequest.RenderMode.OPENGL) // camera render mode
            .setDefaultRotateType(RotateType.ANGLE_0) // rotate camera image when opengl mode
            .setAudioSource(CameraRequest.AudioSource.SOURCE_AUTO) // set audio source
            .setAspectRatioShow(true) // aspect render,default is true
            .setCaptureRawImage(false) // capture raw image picture when opengl mode
            .setRawPreviewData(false)  // preview raw image when opengl mode
            .create()
    }

    override fun getGravity(): Int = Gravity.TOP
}