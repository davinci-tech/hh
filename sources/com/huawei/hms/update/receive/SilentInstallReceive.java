package com.huawei.hms.update.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.huawei.hms.ui.SafeIntent;
import com.huawei.hms.update.UpdateConstants;

/* loaded from: classes9.dex */
public class SilentInstallReceive extends BroadcastReceiver {

    /* renamed from: a, reason: collision with root package name */
    private Handler f6071a;

    public SilentInstallReceive(Handler handler) {
        this.f6071a = handler;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Bundle bundle;
        if (intent == null) {
            return;
        }
        SafeIntent safeIntent = new SafeIntent(intent);
        String str = null;
        try {
            bundle = safeIntent.getExtras();
        } catch (Throwable unused) {
            bundle = null;
        }
        try {
            str = safeIntent.getAction();
        } catch (Throwable unused2) {
            Log.w("SilentInstallReceive", "onReceive, throwable occur.");
            if (bundle != null) {
            }
            Log.w("SilentInstallReceive", "bundle or action is empty. ");
            return;
        }
        if (bundle != null || TextUtils.isEmpty(str)) {
            Log.w("SilentInstallReceive", "bundle or action is empty. ");
            return;
        }
        if (UpdateConstants.DOWNLOAD_STATUS_ACTION.equals(str)) {
            Message message = new Message();
            message.what = 101;
            message.obj = bundle;
            this.f6071a.sendMessage(message);
            return;
        }
        if (UpdateConstants.DOWNLOAD_PROGRESS_ACTION.equals(str)) {
            Message message2 = new Message();
            message2.what = 102;
            message2.obj = bundle;
            this.f6071a.sendMessage(message2);
            return;
        }
        if (UpdateConstants.INSTALL_ACTION.equals(str)) {
            Message message3 = new Message();
            message3.what = 103;
            message3.obj = bundle;
            this.f6071a.sendMessage(message3);
        }
    }
}
