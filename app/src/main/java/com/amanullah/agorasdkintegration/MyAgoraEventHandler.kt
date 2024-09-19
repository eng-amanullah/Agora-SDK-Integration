package com.amanullah.agorasdkintegration

import android.util.Log
import io.agora.rtc2.IRtcEngineEventHandler

class AgoraEventHandler : IRtcEngineEventHandler() {

    // Callbacks for Agora events
    override fun onJoinChannelSuccess(channel: String?, uid: Int, elapsed: Int) {
        super.onJoinChannelSuccess(channel, uid, elapsed)
        println("Successfully joined channel: $channel with uid: $uid")
    }

    override fun onUserOffline(uid: Int, reason: Int) {
        super.onUserOffline(uid, reason)
        println("User offline: $uid Reason: $reason")
    }

    override fun onUserJoined(uid: Int, elapsed: Int) {
        // Callback when a new user joins the channel
        Log.d("AgoraCallback", "User joined with UID: $uid")
    }

    override fun onError(err: Int) {
        super.onError(err)
        println("Error: $err")
    }
}
