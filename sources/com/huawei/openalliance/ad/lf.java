package com.huawei.openalliance.ad;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import com.huawei.openalliance.ad.activity.PPSNotificationActivity;
import com.huawei.openalliance.ad.constant.AdShowExtras;
import com.huawei.openalliance.ad.constant.MapKeyNames;
import com.huawei.openalliance.ad.download.app.AppDownloadTask;
import com.huawei.openalliance.ad.inter.data.AppInfo;

/* loaded from: classes5.dex */
public class lf extends lj {
    private AppDownloadTask c;
    private AppInfo d;

    @Override // com.huawei.openalliance.ad.lj
    protected PendingIntent f() {
        return b("com.huawei.ads.notification.action.CLICK");
    }

    @Override // com.huawei.openalliance.ad.lj
    protected String e() {
        AppInfo appInfo = this.d;
        return appInfo != null ? com.huawei.openalliance.ad.utils.cz.c(appInfo.d()) : "";
    }

    @Override // com.huawei.openalliance.ad.lj
    protected String d() {
        return "AppInstalledNotification";
    }

    @Override // com.huawei.openalliance.ad.lj
    protected String c() {
        AppInfo appInfo = this.d;
        return appInfo != null ? appInfo.getAppName() : "";
    }

    @Override // com.huawei.openalliance.ad.lj
    public void b() {
        if (h()) {
            ll.a(this.f7185a).a(this.d.getPackageName());
            super.b();
            AppDownloadTask appDownloadTask = this.c;
            if (appDownloadTask == null || appDownloadTask.C() == null || !com.huawei.openalliance.ad.utils.dd.x(this.f7185a)) {
                return;
            }
            new com.huawei.openalliance.ad.analysis.h(this.f7185a).c(this.c.C().a(), "0");
        }
    }

    @Override // com.huawei.openalliance.ad.lj
    void a(Notification.Builder builder) {
        if (builder == null || !g()) {
            return;
        }
        b(builder);
        builder.setDeleteIntent(a("com.huawei.ads.notification.action.DELETE"));
    }

    @Override // com.huawei.openalliance.ad.lj
    int a() {
        if (g()) {
            return this.d.getPackageName().hashCode();
        }
        return 1;
    }

    private boolean h() {
        AppInfo appInfo = this.d;
        return (appInfo == null || appInfo.m() != 1 || TextUtils.isEmpty(this.d.d())) ? false : true;
    }

    private boolean g() {
        AppInfo appInfo = this.d;
        return (appInfo == null || TextUtils.isEmpty(appInfo.getPackageName())) ? false : true;
    }

    private void b(Notification.Builder builder) {
        Drawable loadIcon;
        if (!g() || this.f7185a == null) {
            return;
        }
        PackageInfo b = com.huawei.openalliance.ad.utils.i.b(this.f7185a, this.d.getPackageName());
        if (b.applicationInfo == null || (loadIcon = b.applicationInfo.loadIcon(this.f7185a.getPackageManager())) == null) {
            return;
        }
        builder.setLargeIcon(com.huawei.openalliance.ad.utils.az.a(loadIcon));
    }

    private PendingIntent b(String str) {
        if (!g()) {
            return null;
        }
        Intent intent = new Intent(this.f7185a, (Class<?>) PPSNotificationActivity.class);
        intent.setAction(str);
        intent.putExtra("type", 1);
        intent.putExtra("appInfo", this.d);
        AppDownloadTask appDownloadTask = this.c;
        if (appDownloadTask != null) {
            intent.putExtra(AdShowExtras.DOWNLOAD_SOURCE, appDownloadTask.F());
            if (this.c.C() != null) {
                intent.putExtra(MapKeyNames.CONTENT_RECORD, this.c.C().a());
            }
        }
        return PendingIntent.getActivity(this.f7185a, a(), intent, 201326592);
    }

    private PendingIntent a(String str) {
        if (!g()) {
            return null;
        }
        Intent intent = new Intent();
        intent.setAction(str);
        intent.setPackage(this.f7185a.getPackageName());
        intent.putExtra("type", 1);
        intent.putExtra("appInfo", this.d);
        AppDownloadTask appDownloadTask = this.c;
        if (appDownloadTask != null) {
            intent.putExtra(AdShowExtras.DOWNLOAD_SOURCE, appDownloadTask.F());
            if (this.c.C() != null) {
                intent.putExtra(MapKeyNames.CONTENT_RECORD, this.c.C().a());
            }
        }
        return PendingIntent.getBroadcast(this.f7185a, a(), intent, 201326592);
    }

    public lf(Context context, AppDownloadTask appDownloadTask) {
        super(context);
        this.c = appDownloadTask;
        this.d = appDownloadTask.B();
    }
}
