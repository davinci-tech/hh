package com.huawei.hms.push;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.android.HwBuildEx;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.huawei.hms.support.api.push.TransActivity;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.ResourceLoaderUtil;

/* loaded from: classes9.dex */
public class n {

    /* renamed from: a, reason: collision with root package name */
    private static int f5680a;

    public static void a(Context context, o oVar) {
        int hashCode;
        int hashCode2;
        int i;
        int i2;
        synchronized (n.class) {
            if (context != null) {
                if (!a(oVar)) {
                    HMSLog.d("PushSelfShowLog", "showNotification, the msg id = " + oVar.o());
                    if (f5680a == 0) {
                        f5680a = (context.getPackageName() + System.currentTimeMillis()).hashCode();
                    }
                    if (TextUtils.isEmpty(oVar.k())) {
                        String p = oVar.p();
                        if (!TextUtils.isEmpty(p)) {
                            int hashCode3 = p.hashCode();
                            oVar.a(hashCode3);
                            HMSLog.d("PushSelfShowLog", "notification msgTag = " + hashCode3);
                        }
                        if (oVar.r() != -1) {
                            hashCode = oVar.r();
                            i2 = (oVar.j() + System.currentTimeMillis()).hashCode();
                            i = i2 + 1;
                            hashCode2 = (oVar.r() + oVar.j() + context.getPackageName()).hashCode();
                        } else {
                            int i3 = f5680a;
                            int i4 = i3 + 1;
                            int i5 = i3 + 2;
                            int i6 = i3 + 3;
                            int i7 = i3 + 4;
                            f5680a = i7;
                            hashCode2 = i7;
                            hashCode = i4;
                            i2 = i5;
                            i = i6;
                        }
                    } else {
                        hashCode = (oVar.k() + oVar.j()).hashCode();
                        int i8 = f5680a;
                        int i9 = i8 + 1;
                        int i10 = i8 + 2;
                        f5680a = i10;
                        hashCode2 = (oVar.k() + oVar.j() + context.getPackageName()).hashCode();
                        i = i10;
                        i2 = i9;
                    }
                    HMSLog.d("PushSelfShowLog", "notifyId:" + hashCode + ",openNotifyId:" + i2 + ",delNotifyId:" + i + ",alarmNotifyId:" + hashCode2);
                    int[] iArr = new int[4];
                    iArr[0] = hashCode;
                    iArr[1] = i2;
                    iArr[2] = i;
                    if (oVar.e() <= 0) {
                        hashCode2 = 0;
                    }
                    iArr[3] = hashCode2;
                    Notification a2 = e.d() ? a(context, oVar, iArr) : null;
                    NotificationManager notificationManager = (NotificationManager) context.getSystemService(RemoteMessageConst.NOTIFICATION);
                    if (notificationManager != null && a2 != null) {
                        notificationManager.createNotificationChannel(new NotificationChannel("HwPushChannelID", context.getString(ResourceLoaderUtil.getStringId("hms_push_channel")), 3));
                        notificationManager.notify(hashCode, a2);
                        l.a(context, oVar.o(), oVar.b(), "100");
                    }
                }
            }
        }
    }

    private static PendingIntent b(Context context, o oVar, int[] iArr) {
        return PendingIntent.getBroadcast(context, iArr[2], a(context, oVar, iArr, "2", 268435456), e.a());
    }

    private static PendingIntent c(Context context, o oVar, int[] iArr) {
        Intent a2 = a(context, oVar, iArr, "1", 268435456);
        if (!a()) {
            return PendingIntent.getBroadcast(context, iArr[1], a2, e.a());
        }
        a2.setClass(context, TransActivity.class);
        a2.setFlags(268468224);
        return PendingIntent.getActivity(context, iArr[1], a2, e.a());
    }

    private static void d(o oVar, Notification.Builder builder) {
        String t = oVar.t();
        String i = oVar.i();
        if (TextUtils.isEmpty(i)) {
            builder.setContentText(t);
            return;
        }
        builder.setContentText(i);
        if (TextUtils.isEmpty(t)) {
            return;
        }
        builder.setContentTitle(t);
    }

    private static void b(Context context, Notification.Builder builder, o oVar) {
        if ("com.huawei.android.pushagent".equals(context.getPackageName())) {
            Bundle bundle = new Bundle();
            String j = oVar.j();
            if (TextUtils.isEmpty(j)) {
                return;
            }
            bundle.putString("hw_origin_sender_package_name", j);
            builder.setExtras(bundle);
        }
    }

    private static void c(o oVar, Notification.Builder builder) {
        builder.setTicker(oVar.w());
    }

    private static void b(o oVar, Notification.Builder builder) {
        String s = oVar.s();
        if (TextUtils.isEmpty(s)) {
            return;
        }
        builder.setSubText(s);
    }

    private static boolean a() {
        return Build.VERSION.SDK_INT >= 30;
    }

    private static Intent a(Context context, o oVar, int[] iArr, String str, int i) {
        Intent intent = new Intent("com.huawei.intent.action.PUSH_DELAY_NOTIFY");
        intent.putExtra("selfshow_info", oVar.n()).putExtra("selfshow_token", oVar.x()).putExtra("selfshow_event_id", str).putExtra("selfshow_notify_id", iArr[0]).putExtra("selfshow_auto_clear_id", iArr[3]).setPackage(context.getPackageName()).setFlags(i);
        return intent;
    }

    private static Notification a(Context context, o oVar, int[] iArr) {
        Notification.Builder builder = new Notification.Builder(context);
        if (j.a(oVar) == k.STYLE_BIGTEXT) {
            j.a(builder, oVar.f(), oVar);
        }
        h.a(context, builder, oVar);
        b(oVar, builder);
        d(oVar, builder);
        a(context, oVar, builder);
        a(builder);
        a(oVar, builder);
        c(oVar, builder);
        builder.setContentIntent(c(context, oVar, iArr));
        builder.setDeleteIntent(b(context, oVar, iArr));
        builder.setChannelId("HwPushChannelID");
        b(context, builder, oVar);
        a(context, builder, oVar);
        return builder.build();
    }

    private static void a(Context context, Notification.Builder builder, o oVar) {
        if (HwBuildEx.VERSION.EMUI_SDK_INT < 11 || !e.a(context)) {
            return;
        }
        Bundle bundle = new Bundle();
        String j = oVar.j();
        HMSLog.i("PushSelfShowLog", "the package name of notification is:" + j);
        if (!TextUtils.isEmpty(j)) {
            String a2 = e.a(context, j);
            HMSLog.i("PushSelfShowLog", "the app name is:" + a2);
            if (a2 != null) {
                bundle.putCharSequence("android.extraAppName", a2);
            }
        }
        builder.setExtras(bundle);
    }

    private static void a(Context context, o oVar, Notification.Builder builder) {
        Bitmap a2 = h.a(context, oVar);
        if (a2 != null) {
            builder.setLargeIcon(a2);
        }
    }

    private static void a(Notification.Builder builder) {
        builder.setShowWhen(true);
        builder.setWhen(System.currentTimeMillis());
    }

    private static void a(o oVar, Notification.Builder builder) {
        builder.setAutoCancel(oVar.d() == 1);
        builder.setOngoing(false);
    }

    private static boolean a(o oVar) {
        return oVar == null || (TextUtils.isEmpty(oVar.t()) && TextUtils.isEmpty(oVar.i()));
    }
}
