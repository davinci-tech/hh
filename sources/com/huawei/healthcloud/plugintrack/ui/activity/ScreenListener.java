package com.huawei.healthcloud.plugintrack.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.huawei.haf.application.BroadcastManager;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.guk;

/* loaded from: classes4.dex */
public class ScreenListener {
    private ScreenStateListener c;
    private b e;

    public interface ScreenStateListener {
        void onScreenOff();

        void onScreenOn();

        void onUserPresent();
    }

    public ScreenListener(Context context) {
        if (context == null) {
            throw new RuntimeException("ScreenListener invalid params.");
        }
        this.e = new b();
    }

    public void b(ScreenStateListener screenStateListener) {
        this.c = screenStateListener;
        BroadcastManager.wm_(this.e);
    }

    public void c() {
        BroadcastManager.wA_(this.e);
        guk.e(false);
    }

    class b extends BroadcastReceiver {
        private b() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || context == null) {
                return;
            }
            String action = intent.getAction();
            if ("android.intent.action.SCREEN_ON".equals(action)) {
                if (ScreenListener.this.c != null) {
                    ScreenListener.this.c.onScreenOn();
                }
                guk.e(false);
            } else if ("android.intent.action.SCREEN_OFF".equals(action)) {
                if (ScreenListener.this.c != null) {
                    ScreenListener.this.c.onScreenOff();
                }
                guk.e(true);
            } else if ("android.intent.action.USER_PRESENT".equals(action)) {
                if (ScreenListener.this.c != null) {
                    ScreenListener.this.c.onUserPresent();
                }
            } else {
                LogUtil.a("Track_ScreenListener", "unknow action");
            }
            LogUtil.h("Track_ScreenListener", "ScreenBroadcastReceiver intent:", intent.getAction());
        }
    }
}
