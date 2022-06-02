package com.elvitalya.makeiteasy.view.camera_capture

import android.content.Context
import androidx.lifecycle.ViewModel
import com.elvitalya.makeiteasy.R
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.File
import javax.inject.Inject

@HiltViewModel
class CameraScreenViewModel @Inject constructor() : ViewModel() {

    fun getDirectory(context: Context): File {
        val mediaDir = context.externalMediaDirs.firstOrNull()?.let {
            File(it, context.resources.getString(R.string.app_name)).apply { mkdirs() }
        }

        return if(mediaDir != null && mediaDir.exists()) mediaDir else context.filesDir
    }
}