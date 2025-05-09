package defpackage;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.RemoteViews;
import androidx.core.app.NotificationManagerCompat;
import com.huawei.haf.common.utils.ScreenUtil;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevicemgr.R$string;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.UnitUtil;

/* loaded from: classes5.dex */
public class jkn {

    /* renamed from: a, reason: collision with root package name */
    private static jkn f13916a;
    private static final Object d = new Object();
    private String b;
    private boolean c = false;
    private int e = -1;
    private RemoteViews j;

    public static jkn a() {
        jkn jknVar;
        synchronized (d) {
            if (f13916a == null) {
                f13916a = new jkn();
            }
            jknVar = f13916a;
        }
        return jknVar;
    }

    public void c(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("UpdateNotificationUtil", "createNotification parameter error");
            return;
        }
        this.b = str;
        RemoteViews remoteViews = new RemoteViews(BaseApplication.getContext().getPackageName(), R.layout.device_ota_update_notification);
        this.j = remoteViews;
        bHU_(remoteViews);
        this.c = true;
    }

    private void bHU_(RemoteViews remoteViews) {
        Intent intent = new Intent();
        intent.putExtra("device_id", this.b);
        intent.setClassName(BaseApplication.getContext(), "com.huawei.ui.device.activity.update.UpdateVersionActivity");
        Notification.Builder style = jdh.c().xf_().setSmallIcon(R.drawable.healthlogo_ic_notification).setContentIntent(PendingIntent.getActivity(BaseApplication.getContext(), 0, intent, 201326592)).setCustomContentView(remoteViews).setCustomBigContentView(remoteViews).setShowWhen(true).setAutoCancel(true).setPriority(0).setOngoing(true).setWhen(System.currentTimeMillis()).setStyle(new Notification.DecoratedCustomViewStyle());
        style.setGroup("UpdateNotificationUtil");
        if (c()) {
            jdh.c().xh_(100009, style.build());
        }
    }

    public void c(int i, int i2) {
        String string;
        String string2;
        if (!this.c) {
            LogUtil.h("UpdateNotificationUtil", "notifyProgress, not create");
            return;
        }
        if (i2 > 100 || i2 < 0) {
            LogUtil.h("UpdateNotificationUtil", "notifyProgress parameter error");
            return;
        }
        if (i2 == this.e) {
            LogUtil.h("UpdateNotificationUtil", "notifyProgress progress not change");
            return;
        }
        if (!ScreenUtil.a()) {
            LogUtil.h("UpdateNotificationUtil", "notifyProgress screen is off");
            return;
        }
        this.e = i2;
        String e = UnitUtil.e(i2, 2, 0);
        if (EnvironmentInfo.j() || CommonUtil.bm()) {
            a(i, i2);
            return;
        }
        if (i == 1) {
            string = BaseApplication.getContext().getString(R$string.IDS_device_auto_update_transfer);
            string2 = BaseApplication.getContext().getString(R$string.IDS_device_auto_update_transfer_percent, e);
            LogUtil.a("UpdateNotificationUtil", "notifyProgress:", Integer.valueOf(i2), " transferred");
        } else if (i == 2) {
            string = BaseApplication.getContext().getString(R$string.IDS_device_auto_update_download);
            string2 = BaseApplication.getContext().getString(R$string.IDS_device_auto_update_download_percent, e);
            LogUtil.a("UpdateNotificationUtil", "notifyProgress:", Integer.valueOf(i2), " downloaded");
        } else {
            LogUtil.h("UpdateNotificationUtil", "notifyProgress invaild progressType");
            return;
        }
        this.j.setTextViewText(R.id.touch_transfer_percent, string2);
        this.j.setTextViewText(R.id.touch_transfer_title, string);
        this.j.setProgressBar(R.id.touch_transfer_progress, 100, i2, false);
        bHU_(this.j);
    }

    private void a(int i, int i2) {
        String e = UnitUtil.e(i2, 2, 0);
        Notification.Builder xf_ = jdh.c().xf_();
        xf_.setSmallIcon(R.drawable.healthlogo_ic_notification);
        xf_.setShowWhen(true);
        xf_.setContentIntent(jdh.bFr_(BaseApplication.getContext()));
        xf_.setAutoCancel(true);
        xf_.setOngoing(true);
        xf_.setGroup("UpdateNotificationUtil");
        xf_.setWhen(System.currentTimeMillis());
        xf_.setProgress(100, i2, false);
        if (i == 1) {
            xf_.setContentTitle(BaseApplication.getContext().getString(R$string.IDS_device_auto_update_transfer));
            xf_.setContentText(BaseApplication.getContext().getString(R$string.IDS_device_auto_update_transfer_percent, e));
            LogUtil.a("UpdateNotificationUtil", "notifyProgress:", Integer.valueOf(i2), " transferred");
        } else if (i == 2) {
            xf_.setContentTitle(BaseApplication.getContext().getString(R$string.IDS_device_auto_update_download_title));
            xf_.setContentText(BaseApplication.getContext().getString(R$string.IDS_device_auto_update_download_percent, e));
            LogUtil.a("UpdateNotificationUtil", "notifyProgress:", Integer.valueOf(i2), " downloaded");
        } else {
            LogUtil.h("UpdateNotificationUtil", "notifyProgressWithHarmony4 with invalid progressType");
            return;
        }
        Notification build = xf_.build();
        build.contentIntent = jdh.bFr_(BaseApplication.getContext());
        build.priority = 0;
        Intent intent = new Intent();
        intent.putExtra("device_id", this.b);
        intent.setClassName(BaseApplication.getContext(), "com.huawei.ui.device.activity.update.UpdateVersionActivity");
        build.contentIntent = PendingIntent.getActivity(BaseApplication.getContext(), 0, intent, 201326592);
        jdh.c().xh_(100009, build);
    }

    public void d(String str, String str2) {
        if (!this.c) {
            LogUtil.h("UpdateNotificationUtil", "showText, not create");
            return;
        }
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.h("UpdateNotificationUtil", "showText parameter is null");
            return;
        }
        LogUtil.a("UpdateNotificationUtil", "showText, description:", str2);
        Notification.Builder xf_ = jdh.c().xf_();
        xf_.setSmallIcon(R.drawable.healthlogo_ic_notification);
        xf_.setContentTitle(str);
        xf_.setContentText(str2);
        xf_.setShowWhen(true);
        xf_.setContentIntent(jdh.bFr_(BaseApplication.getContext()));
        xf_.setAutoCancel(true);
        xf_.setGroup("UpdateNotificationUtil");
        xf_.setWhen(System.currentTimeMillis());
        Notification build = xf_.build();
        build.contentIntent = jdh.bFr_(BaseApplication.getContext());
        build.priority = 0;
        Intent intent = new Intent();
        intent.putExtra("device_id", this.b);
        intent.setClassName(BaseApplication.getContext(), "com.huawei.ui.device.activity.update.UpdateVersionActivity");
        build.contentIntent = PendingIntent.getActivity(BaseApplication.getContext(), 0, intent, 201326592);
        jdh.c().xh_(100009, build);
    }

    public void e() {
        this.e = -1;
        jdh.c().a(100009);
        this.c = false;
    }

    private boolean c() {
        if (CommonUtil.bh() || NotificationManagerCompat.from(BaseApplication.getContext()).areNotificationsEnabled()) {
            return true;
        }
        LogUtil.h("UpdateNotificationUtil", "notifyMsg notification bar permission is not enabled.");
        return false;
    }
}
