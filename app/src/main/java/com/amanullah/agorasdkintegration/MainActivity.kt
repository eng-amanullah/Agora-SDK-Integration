package com.amanullah.agorasdkintegration

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import io.agora.rtc2.Constants
import io.agora.rtc2.RtcEngine

class MainActivity : ComponentActivity() {

    private lateinit var agoraEngine: RtcEngine

    private val appId = "d57222f7c2b14fd38f7ea9a51eb0afe4" // Replace with your App ID
    private val token =
        "007eJxTYFB6kZhwc20m34qFC0q2XX7n+PaGxoyGBz6axx6p8e9uFu5SYEgxNTcyMkozTzZKMjRJSzG2SDNPTbRMNDVMTTJITEs1kWB7mdYQyMiw/LofEyMDBIL47AwlqcUlBgaGDAwANAAhuQ==" // If you are using a token for authentication
    private val channelName = "test001" // Replace with your channel name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RequestAudioPermissionAndInitAgora()
        }
    }

    // Initialize Agora Engine
    fun initAgoraEngine() {
        try {
            agoraEngine = RtcEngine.create(applicationContext, appId, AgoraEventHandler())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // Join Agora Channel
    fun joinChannel() {
        agoraEngine.setChannelProfile(Constants.CHANNEL_PROFILE_COMMUNICATION)
        agoraEngine.joinChannel(token, channelName, "", 0)
    }

    // Leave Agora Channel
    fun leaveChannel() {
        agoraEngine.leaveChannel()
    }

    // Destroy Agora Engine when no longer needed
    override fun onDestroy() {
        super.onDestroy()
        RtcEngine.destroy()
    }
}