package com.huawei.openalliance.ad;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import com.huawei.hms.push.constant.RemoteMessageConst;

/* loaded from: classes5.dex */
public abstract class lj {

    /* renamed from: a, reason: collision with root package name */
    protected Context f7185a;
    NotificationManager b;

    abstract int a();

    abstract void a(Notification.Builder builder);

    abstract String c();

    abstract String e();

    abstract PendingIntent f();

    protected String d() {
        return "BaseNotification";
    }

    public void b() {
        Notification.Builder g = g();
        a(g);
        NotificationChannel notificationChannel = new NotificationChannel(h(), d(), 3);
        notificationChannel.setShowBadge(false);
        notificationChannel.enableLights(false);
        g.setChannelId(h());
        this.b.createNotificationChannel(notificationChannel);
        this.b.notify(a(), g.build());
    }

    private String h() {
        return "hwpps";
    }

    private Notification.Builder g() {
        Notification.Builder builder = new Notification.Builder(this.f7185a);
        builder.setContentTitle(c());
        builder.setContentText(e());
        builder.setWhen(System.currentTimeMillis());
        builder.setShowWhen(true);
        builder.setContentIntent(f());
        builder.setAutoCancel(true);
        ApplicationInfo applicationInfo = this.f7185a.getApplicationInfo();
        if (applicationInfo != null) {
            builder.setSmallIcon(applicationInfo.icon);
        }
        return builder;
    }

    protected lj(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.f7185a = applicationContext;
        this.b = (NotificationManager) applicationContext.getSystemService(RemoteMessageConst.NOTIFICATION);
    }
}
