package com.amanullah.agorasdkintegration

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat

@Composable
fun RequestAudioPermissionAndInitAgora() {
    val context = LocalContext.current
    var hasMicrophonePermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.RECORD_AUDIO
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    // Launcher to request permission
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        hasMicrophonePermission = isGranted
    }

    val activity = context as MainActivity

    LaunchedEffect(hasMicrophonePermission) {
        if (hasMicrophonePermission) {
            activity.initAgoraEngine()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (hasMicrophonePermission) {
            Text(text = "Microphone permission granted")

            Button(
                modifier = Modifier
                    .padding(top = 16.dp),
                onClick = {
                    activity.joinChannel()
                }
            ) {
                Text(text = "Join Agora Channel")
            }


            Button(
                modifier = Modifier
                    .padding(top = 16.dp),
                onClick = {
                    activity.leaveChannel()
                }
            ) {
                Text(text = "Leave Agora Channel")
            }
        } else {
            Text(
                text = "Microphone permission not granted"
            )

            Button(
                modifier = Modifier
                    .padding(top = 16.dp),
                onClick = {
                    requestPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
                }
            ) {
                Text("Request Microphone Permission")
            }
        }
    }
}
