package com.huawei.watchface;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import androidx.core.internal.view.SupportMenu;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.huawei.watchface.api.WebViewActivity;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.mvp.model.datatype.TryoutBean;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.DensityUtil;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.IntegerUtils;
import com.huawei.watchface.utils.MobileInfoHelper;

/* loaded from: classes7.dex */
public class fk extends ContextWrapper {

    /* renamed from: a, reason: collision with root package name */
    private static fk f11044a;
    private NotificationManager b;

    public static fk a() {
        fk fkVar;
        synchronized (fk.class) {
            if (f11044a == null) {
                synchronized (fk.class) {
                    f11044a = new fk(Environment.getApplicationContext());
                }
            }
            fkVar = f11044a;
        }
        return fkVar;
    }

    private fk(Context context) {
        super(context);
    }

    private void b() {
        NotificationChannel notificationChannel = new NotificationChannel("tryout_watch_face", DensityUtil.getStringById(R$string.trial), 3);
        notificationChannel.canBypassDnd();
        notificationChannel.enableLights(false);
        notificationChannel.setLockscreenVisibility(-1);
        notificationChannel.setLightColor(SupportMenu.CATEGORY_MASK);
        notificationChannel.canShowBadge();
        notificationChannel.enableVibration(false);
        notificationChannel.enableLights(false);
        notificationChannel.getAudioAttributes();
        notificationChannel.getGroup();
        notificationChannel.setBypassDnd(true);
        notificationChannel.shouldShowLights();
        NotificationManager c = c();
        if (c != null) {
            c.createNotificationChannel(notificationChannel);
        }
    }

    private NotificationManager c() {
        Application applicationContext;
        if (this.b == null && (applicationContext = Environment.getApplicationContext()) != null) {
            this.b = (NotificationManager) applicationContext.getSystemService(RemoteMessageConst.NOTIFICATION);
        }
        return this.b;
    }

    public Notification a(TryoutBean tryoutBean) {
        if (tryoutBean == null) {
            HwLog.w("TryOutNotificationUtils", "getTryOutNotification -- dataBundle is null");
            return null;
        }
        Application applicationContext = Environment.getApplicationContext();
        NotificationCompat.Builder a2 = a(DensityUtil.getStringById(R$string.trial_now_take_away_watchface, tryoutBean.getShowTitle()));
        if (a2 == null) {
            HwLog.e("TryOutNotificationUtils", "getTryOutNotification builder is null");
            return null;
        }
        a2.addAction(-1, DensityUtil.getStringById(R$string.try_notice_button_end), a(applicationContext, tryoutBean, "2"));
        if (IntegerUtils.a(tryoutBean.getResourceType()) == 4) {
            a2.addAction(-1, DensityUtil.getStringById(R$string.vip_open_immediately), a(applicationContext, tryoutBean, "3"));
        } else {
            a2.addAction(-1, DensityUtil.getStringById(R$string.try_notice_button_buynow), a(applicationContext, tryoutBean, "1"));
        }
        Notification build = a2.build();
        build.flags |= 32;
        return build;
    }

    public void b(TryoutBean tryoutBean) {
        NotificationManager c = c();
        if (c == null) {
            HwLog.e("TryOutNotificationUtils", "sendNotification getManager is null");
        } else {
            c.notify(100, a(tryoutBean));
        }
    }

    private PendingIntent a(Context context, TryoutBean tryoutBean, String str) {
        Intent intent = new Intent(context, (Class<?>) WebViewActivity.class);
        intent.setFlags(268435456);
        intent.setFlags(AppRouterExtras.COLDSTART);
        intent.setAction(str);
        if (tryoutBean != null) {
            intent.putExtra("key_try_out_bean", tryoutBean);
        }
        try {
            return PendingIntent.getActivity(context, 100001, intent, CommonUtils.G() ? 167772160 : AMapEngineUtils.HALF_MAX_P20_WIDTH);
        } catch (Exception unused) {
            HwLog.e("TryOutNotificationUtils", "getActionIntent Exception");
            return null;
        }
    }

    public NotificationCompat.Builder a(String str) {
        Application applicationContext = Environment.getApplicationContext();
        if (applicationContext == null) {
            HwLog.e("TryOutNotificationUtils", "getNotification applicationContext is null");
            return null;
        }
        b();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(applicationContext, "tryout_watch_face");
        builder.setStyle(new NotificationCompat.BigTextStyle().bigText(str));
        builder.setSmallIcon(R$drawable.watchface_ic_notify_theme);
        builder.setContentText(str);
        if (MobileInfoHelper.isHarmony4Version()) {
            builder.setContentTitle(DensityUtil.getStringById(R$string.trial_dial));
        }
        builder.setShowWhen(true);
        builder.setAutoCancel(false);
        builder.setOngoing(true);
        builder.setVisibility(-1);
        return builder;
    }

    public void a(int i) {
        NotificationManager c = c();
        if (c == null) {
            HwLog.e("TryOutNotificationUtils", "clearNotification getManager is null");
        } else {
            c.cancel(i);
        }
    }
}
