package com.elvitalya.makeiteasy.view.pick_image

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImagePickerViewModel @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : ViewModel() {

    private val pic = stringPreferencesKey("pic")

    var picUri = MutableSharedFlow<Uri?>(replay = 1, extraBufferCapacity = 1)

    fun savePic(uri: Uri?) {
        viewModelScope.launch {
            dataStore.edit {
                it[pic] = uri.toString()
                picUri.emit(uri)
            }
        }
    }

  private  fun getSavedPic() {
        viewModelScope.launch {
            dataStore.data.map {
            picUri.emit(Uri.parse(it[pic]))
                Log.d("CHECK___", "getSavedPic: ${it[pic]}")
            }
        }
    }

    init {
        getSavedPic()
    }
}