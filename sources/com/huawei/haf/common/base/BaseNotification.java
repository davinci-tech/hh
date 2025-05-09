package com.huawei.haf.common.base;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.application.BroadcastManager;
import health.compact.a.CommonUtils;
import health.compact.a.LogUtil;

/* loaded from: classes.dex */
public class BaseNotification {
    private int b;
    private int c;
    private String e;

    protected void bFx_(NotificationManager notificationManager) {
    }

    class LocaleChangedReceiver extends BroadcastReceiver {
        private LocaleChangedReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            BaseNotification.this.a();
        }
    }

    public void a(String str, int i, int i2) {
        if (TextUtils.isEmpty(str)) {
            str = "channel_common_id";
        }
        this.e = str;
        this.c = i;
        this.b = i2;
        BroadcastManager.wk_(new LocaleChangedReceiver());
        a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        NotificationManager xB_ = CommonUtils.xB_();
        if (xB_ == null) {
            return;
        }
        try {
            xd_(xB_);
        } catch (Exception e) {
            LogUtil.c("HAF_Notification", "createNotificationChannel ex=", LogUtil.a(e));
        }
        try {
            bFx_(xB_);
        } catch (Exception e2) {
            LogUtil.c("HAF_Notification", "deleteAllOldNotificationChannel ex=", LogUtil.a(e2));
        }
    }

    private void xd_(NotificationManager notificationManager) {
        NotificationChannel notificationChannel = new NotificationChannel(this.e, d(), this.b);
        bFy_(notificationChannel);
        notificationManager.createNotificationChannel(notificationChannel);
    }

    private String d() {
        try {
            return BaseApplication.e().getString(this.c);
        } catch (Resources.NotFoundException e) {
            LogUtil.c("HAF_Notification", "getChannelName ex=", LogUtil.a(e));
            return "channel_common_name";
        }
    }

    public void bFy_(NotificationChannel notificationChannel) {
        notificationChannel.enableVibration(false);
        notificationChannel.setSound(null, null);
    }

    public static int e(String str, String str2) {
        Resources resources = BaseApplication.e().getResources();
        int identifier = resources.getIdentifier(str, "string", BaseApplication.d());
        boolean z = identifier != 0;
        if (!z) {
            identifier = resources.getIdentifier(str2, "string", BaseApplication.d());
        }
        LogUtil.c("HAF_Notification", "channelResId=", Integer.valueOf(identifier), ", isFirstName=", Boolean.valueOf(z));
        return identifier;
    }

    public void xi_(Notification.Builder builder) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("hw_disable_ntf_delete_menu", true);
        builder.addExtras(bundle);
    }

    public Notification.Builder xf_() {
        return new Notification.Builder(BaseApplication.e(), this.e);
    }

    public void xh_(int i, Notification notification) {
        NotificationManager xB_ = CommonUtils.xB_();
        if (xB_ != null) {
            xB_.notify(i, notification);
        }
    }

    public void a(int i) {
        NotificationManager xB_ = CommonUtils.xB_();
        if (xB_ != null) {
            xB_.cancel(i);
        }
    }

    public void b() {
        NotificationManager xB_ = CommonUtils.xB_();
        if (xB_ != null) {
            xB_.cancelAll();
        }
    }
}
