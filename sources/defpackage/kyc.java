package defpackage;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Icon;
import android.text.TextUtils;
import android.widget.RemoteViews;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwversionmgr.manager.HwVersionManager;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.R$string;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.LocalBroadcast;
import health.compact.a.UnitUtil;

/* loaded from: classes5.dex */
public class kyc {
    private static kyc c;
    private static final Object e = new Object();
    private b b;
    private PendingIntent d;
    private Notification.Builder f;
    private RemoteViews g;
    private Notification i;
    private boolean h = false;

    /* renamed from: a, reason: collision with root package name */
    private int f14695a = -1;

    class b extends BroadcastReceiver {
        private b() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.h("AppUpdateNotification", "NotificationClickReceiver intent is null");
                return;
            }
            String action = intent.getAction();
            LogUtil.a("AppUpdateNotification", "NotificationClickReceiver action is :", action, " mIsCreate:", Boolean.valueOf(kyc.this.h));
            if (TextUtils.isEmpty(action)) {
                return;
            }
            action.hashCode();
            if (action.equals("cancel_file_download")) {
                HwVersionManager.c(BaseApplication.getContext()).d();
                kyc.this.a();
            }
        }
    }

    public static kyc e() {
        kyc kycVar;
        synchronized (e) {
            if (c == null) {
                LogUtil.a("AppUpdateNotification", "new AppUpdateNotification()");
                c = new kyc();
            }
            kycVar = c;
        }
        return kycVar;
    }

    protected kyc() {
        try {
            this.b = new b();
            BroadcastManagerUtil.bFA_(BaseApplication.getContext(), this.b, new IntentFilter("cancel_file_download"), LocalBroadcast.c, null);
        } catch (IllegalStateException unused) {
            LogUtil.b("AppUpdateNotification", "registerReceiver is error");
        }
    }

    public void c() {
        Notification.Builder xf_ = jdh.c().xf_();
        this.f = xf_;
        xf_.setSmallIcon(R$drawable.healthlogo_ic_notification);
        this.f.setShowWhen(true);
        this.f.setContentIntent(jdh.bFr_(BaseApplication.getContext()));
        this.f.setAutoCancel(true);
        this.f.setGroup("AppUpdateNotification");
        this.f.setWhen(System.currentTimeMillis());
        Notification build = this.f.build();
        build.flags |= 2;
        build.contentIntent = jdh.bFr_(BaseApplication.getContext());
        build.priority = 0;
        if (CommonUtil.bm()) {
            Intent intent = new Intent("cancel_file_download");
            intent.setPackage(BaseApplication.getAppPackage());
            this.d = PendingIntent.getBroadcast(BaseApplication.getContext(), 0, intent, 201326592);
            this.f.addAction(new Notification.Action.Builder((Icon) null, nsf.h(R$string.IDS_notify_cancel), this.d).build());
            this.i = build;
            jdh.c().xh_(20200317, build);
            this.h = true;
            return;
        }
        if (!CommonUtil.bh() || (CommonUtil.ck() && !EnvironmentInfo.j())) {
            RemoteViews remoteViews = new RemoteViews(BaseApplication.getContext().getPackageName(), R.layout.device_update_notification);
            if (CommonUtil.ar() || !CommonUtil.co()) {
                int c2 = nsn.c(BaseApplication.getContext(), 16.0f);
                remoteViews.setViewPadding(R.id.add_music_to_watch_layout, c2, 0, c2, 0);
            }
            Intent intent2 = new Intent("cancel_file_download");
            intent2.setPackage(BaseApplication.getAppPackage());
            PendingIntent broadcast = PendingIntent.getBroadcast(BaseApplication.getContext(), 0, intent2, 201326592);
            this.d = broadcast;
            remoteViews.setOnClickPendingIntent(R.id.progress_cancel, broadcast);
            build.contentView = remoteViews;
            this.g = remoteViews;
        }
        this.i = build;
        jdh.c().xh_(20200317, build);
        this.h = true;
    }

    public void c(String str, int i) {
        if (!this.h) {
            LogUtil.h("AppUpdateNotification", "notifyProgress, not create");
            return;
        }
        if (i > 100 || i < 0 || TextUtils.isEmpty(str)) {
            LogUtil.h("AppUpdateNotification", "notifyProgress parameter error");
            return;
        }
        if (i == this.f14695a) {
            LogUtil.h("AppUpdateNotification", "notifyProgress progress not change");
            return;
        }
        if (CommonUtil.bm()) {
            e(i, str);
        } else {
            b(i, str);
        }
        jdh.c().xh_(20200317, this.i);
    }

    private void e(int i, String str) {
        this.f.setContentTitle(nsf.h(R$string.IDS_app_name_health)).setContentText(str + i + nsf.h(R$string.IDS_bolt_percent_unit)).setSmallIcon(R$drawable.healthlogo_ic_notification).setProgress(100, i, false);
        this.i = this.f.build();
    }

    private void b(int i, String str) {
        if (!CommonUtil.bh() || (CommonUtil.ck() && !EnvironmentInfo.j())) {
            this.f14695a = i;
            this.g.setTextViewText(R.id.touch_transfer_percent, UnitUtil.e(i, 2, 0));
            this.g.setTextViewText(R.id.touch_transfer_title, str);
            LogUtil.a("AppUpdateNotification", "notifyProgress:", Integer.valueOf(i));
            this.g.setProgressBar(R.id.touch_transfer_progress, 100, i, false);
            this.i.contentView = this.g;
            return;
        }
        if (EnvironmentInfo.j()) {
            String e2 = UnitUtil.e(i, 2, 0);
            LogUtil.a("AppUpdateNotification", "notifyProgress:", Integer.valueOf(i), "transferPercent :", e2);
            this.f.setContentTitle(nsf.h(R$string.IDS_notify_updating_health));
            this.f.setContentText(nsf.b(R$string.IDS_notify_updated, e2)).setProgress(100, i, false);
        } else {
            this.f.setContentTitle(str).setProgress(100, i, false);
        }
        this.i = this.f.build();
    }

    public void a(String str, String str2) {
        if (!this.h) {
            LogUtil.h("AppUpdateNotification", "showText, not create");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("AppUpdateNotification", "showText title is null");
            return;
        }
        LogUtil.a("AppUpdateNotification", "showText, description:", str2);
        if (CommonUtil.ck() && !EnvironmentInfo.j()) {
            RemoteViews remoteViews = new RemoteViews(BaseApplication.getContext().getPackageName(), R.layout.notification_transfer_end_view);
            remoteViews.setTextViewText(R.id.touch_transfer_end_title, str);
            if (!TextUtils.isEmpty(str2)) {
                remoteViews.setTextViewText(R.id.touch_transfer_content, str2);
            }
            remoteViews.setImageViewResource(R.id.touch_app_image_view, R$drawable.healthlogo_ic_notification);
            remoteViews.setTextViewText(R.id.touch_app_name, BaseApplication.getContext().getString(R$string.IDS_app_name_health));
            this.i.contentView = remoteViews;
        } else {
            if (EnvironmentInfo.j()) {
                this.f.setContentTitle(nsf.h(R$string.IDS_notify_updating_health_download_failed));
            } else {
                this.f.setContentTitle(str);
            }
            this.f.setContentText(str2);
            this.i = this.f.build();
        }
        jdh.c().xh_(20200317, this.i);
    }

    public void a() {
        jdh.c().a(20200317);
        this.h = false;
    }

    public boolean d() {
        return this.h;
    }
}
