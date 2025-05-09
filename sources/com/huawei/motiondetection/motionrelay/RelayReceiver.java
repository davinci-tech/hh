package com.huawei.motiondetection.motionrelay;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import com.huawei.motiondetection.MRLog;

/* loaded from: classes5.dex */
class RelayReceiver extends BroadcastReceiver {
    private static final String TAG = "RelayReceiver";
    private Handler mHandler;
    private Message mMsg = null;

    protected RelayReceiver(Handler handler) {
        this.mHandler = handler;
    }

    protected void destroy() {
        this.mHandler = null;
        this.mMsg = null;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        MRLog.d(TAG, "onReceive intent.getAction: " + intent.getAction());
        try {
            Message obtain = Message.obtain();
            this.mMsg = obtain;
            obtain.what = 1;
            this.mMsg.obj = intent;
            this.mHandler.sendMessage(this.mMsg);
        } catch (Exception e) {
            MRLog.w(TAG, e.getMessage());
        }
    }
}
