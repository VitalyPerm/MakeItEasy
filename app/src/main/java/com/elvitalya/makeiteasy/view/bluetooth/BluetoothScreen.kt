package com.elvitalya.makeiteasy.view.bluetooth

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanFilter
import android.bluetooth.le.ScanResult
import android.bluetooth.le.ScanSettings
import android.content.Context
import android.os.ParcelUuid
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver

@SuppressLint("MissingPermission")
@Composable
fun BluetoothScreen() {

    val TAG = remember { "BluetoothScreen" }
    val context = LocalContext.current
    val btAdapter = remember {
        (context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager).adapter
    }

    val devices = remember { mutableStateListOf<BluetoothDevice>() }

    val lifecycleState by LocalLifecycleOwner.current.lifecycle.observeAsState()

    var granted by remember { mutableStateOf(false) }
    var needToAskPermission by remember { mutableStateOf(true) }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { permissionGranted ->
            val allGranted = permissionGranted.values.find { !it } ?: true
            granted = allGranted
        }
    )

    var btInWork by remember { mutableStateOf(false) }

    val scanCallback = remember {
        object : ScanCallback() {
            override fun onScanResult(callbackType: Int, result: ScanResult?) {
                super.onScanResult(callbackType, result)
                try {
                    result?.device?.let { devices.add(it) }
                    Log.d(TAG, "onScanResult: ${result?.device?.name}")
                } catch (e: SecurityException) {
                    Log.d(TAG, "onScanResult: ERROR ${e.message}")
                }
            }
        }
    }

    LaunchedEffect(key1 = lifecycleState) {
        when (lifecycleState) {
            Lifecycle.Event.ON_START -> {
                if (needToAskPermission) {
                    permissionLauncher.launch(
                        arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION)
                    )
                    needToAskPermission = false
                }

                if (!btInWork) {
                    try {
                        if (!btAdapter.isEnabled) btAdapter.enable()
                        val scanFilter = ScanFilter.Builder().build()
                        val scanFilters: MutableList<ScanFilter> = mutableListOf()
                        scanFilters.add(scanFilter)
                        val scanSettings =
                            ScanSettings.Builder()
                                .setScanMode(ScanSettings.SCAN_MODE_BALANCED)
                                .build()

                        btAdapter.bluetoothLeScanner.startScan(
                            scanFilters,
                            scanSettings,
                            scanCallback
                        )

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }


    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            items(devices.toList()) {

                Surface(
                    color = Color.Cyan,
                    contentColor = Color.White,
                    shape = RoundedCornerShape(16.dp),
                    border = BorderStroke(2.dp, Color.Red),
                    elevation = 4.dp,
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text(
                        text = it.name ?: "",
                        fontSize = 24.sp,
                        modifier = Modifier
                            .padding(16.dp)
                    )
                }
            }
        }
    }

}


@Composable
fun Lifecycle.observeAsState(): State<Lifecycle.Event> {
    val state = remember { mutableStateOf(Lifecycle.Event.ON_ANY) }
    DisposableEffect(this) {
        val observer = LifecycleEventObserver { _, event ->
            state.value = event
        }
        this@observeAsState.addObserver(observer)
        onDispose {
            this@observeAsState.removeObserver(observer)
        }
    }
    return state
}