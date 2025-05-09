package defpackage;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.widget.RemoteViews;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.bundle.AppBundleLauncher;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.manager.powerkit.PowerKitManager;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.coresleep.notification.HealthCommondReceiver;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import health.compact.a.EnvironmentInfo;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

/* loaded from: classes6.dex */
public class ppk {
    public static void e(final Context context) {
        LogUtil.a("SleepNotificationHelper", "pullUpAlarm start");
        if (!efb.b(context)) {
            LogUtil.a("SleepNotificationHelper", "pullUpAlarm not supported");
            return;
        }
        final mtp d = mtp.d();
        if (!d.isPluginAvaiable()) {
            LogUtil.a("SleepNotificationHelper", "PluginSleepDetectionProxy is not available,load plugin");
            d.loadPlugin(new AppBundleLauncher.InstallCallback() { // from class: ppr
                @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
                public final boolean call(Context context2, Intent intent) {
                    return ppk.drR_(context, d, context2, intent);
                }
            });
        } else {
            e(context, d);
        }
    }

    static /* synthetic */ boolean drR_(Context context, mtp mtpVar, Context context2, Intent intent) {
        LogUtil.a("SleepNotificationHelper", "PluginSleepDetectionProxy loadPlugin success");
        e(context, mtpVar);
        return true;
    }

    private static void e(final Context context, final mtp mtpVar) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: ppq
            @Override // java.lang.Runnable
            public final void run() {
                ppk.a(mtp.this, context);
            }
        });
    }

    static /* synthetic */ void a(final mtp mtpVar, final Context context) {
        mtpVar.syncSleepAlarmInfo();
        HandlerExecutor.a(new Runnable() { // from class: ppp
            @Override // java.lang.Runnable
            public final void run() {
                ppk.c(mtp.this, context);
            }
        });
    }

    static /* synthetic */ void c(mtp mtpVar, Context context) {
        if (!mtpVar.isOpenNotification()) {
            LogUtil.a("SleepNotificationHelper", "pullUpAlarm isOpenNotification is false");
            a();
        } else {
            a(context);
        }
    }

    private static void a(Context context) {
        long[] g = g();
        long j = g[0];
        long j2 = g[1];
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis < j || currentTimeMillis > j2 || j > j2) {
            LogUtil.a("SleepNotificationHelper", "The current time is out of range.");
            a();
        }
        if (currentTimeMillis > j2) {
            j += 86400000;
            j2 += 86400000;
        }
        long j3 = j;
        long j4 = j2;
        AlarmManager xy_ = CommonUtils.xy_();
        if (xy_ == null) {
            LogUtil.h("SleepNotificationHelper", "manager is null!");
            return;
        }
        LogUtil.h("SleepNotificationHelper", "pullUpAlarm startTime: ", Long.valueOf(j3), " , endTime: ", Long.valueOf(j4));
        ThreadPoolManager.d().execute(new Runnable() { // from class: ppv
            @Override // java.lang.Runnable
            public final void run() {
                PowerKitManager.e().e(ppk.h());
            }
        });
        List<PendingIntent> c = c(context);
        xy_.setRepeating(0, j4, 86400000L, c.get(1));
        xy_.setRepeating(0, j3, 86400000L, c.get(0));
    }

    public static void b() {
        LogUtil.a("SleepNotificationHelper", "handlerNotification");
        final Context e = BaseApplication.e();
        if (!efb.b(e)) {
            c();
            return;
        }
        final mtp d = mtp.d();
        if (d.isPluginAvaiable()) {
            if (!d.isOpenNotification()) {
                LogUtil.a("SleepNotificationHelper", "handlerNotification isOpenNotification = false");
                return;
            } else {
                d.sleepRecordStatus(new IBaseResponseCallback() { // from class: pps
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public final void d(int i, Object obj) {
                        ppk.a(e, d, i, obj);
                    }
                });
                return;
            }
        }
        d(e);
    }

    static /* synthetic */ void a(Context context, mtp mtpVar, int i, Object obj) {
        if (i != 3) {
            d(context);
            mtpVar.disconnect();
        }
    }

    public static void drQ_(Intent intent) {
        if (TextUtils.isEmpty(intent.getStringExtra("timeZoneId"))) {
            b();
            a(BaseApplication.e());
        } else if (sqa.ekw_(intent)) {
            LogUtil.a("SleepNotificationHelper", "handlerNotification timezone has changed.");
            a(BaseApplication.e());
        } else {
            b();
        }
    }

    public static boolean d() {
        long currentTimeMillis = System.currentTimeMillis();
        long[] g = g();
        return currentTimeMillis >= g[0] && currentTimeMillis <= g[1];
    }

    private static long[] g() {
        int sleepTime = mtp.d().getSleepTime();
        int nextInt = new SecureRandom().nextInt(11);
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, sleepTime / 60);
        calendar.set(12, (sleepTime % 60) - (nextInt + 20));
        calendar.set(13, 0);
        calendar.set(14, 0);
        long timeInMillis = calendar.getTimeInMillis();
        return new long[]{timeInMillis, 10800000 + timeInMillis};
    }

    public static void c() {
        a();
        AlarmManager xy_ = CommonUtils.xy_();
        if (xy_ == null) {
            LogUtil.h("SleepNotificationHelper", "manager is null!");
            return;
        }
        List<PendingIntent> c = c(BaseApplication.e());
        xy_.cancel(c.get(0));
        xy_.cancel(c.get(1));
        ThreadPoolManager.d().execute(new Runnable() { // from class: ppo
            @Override // java.lang.Runnable
            public final void run() {
                PowerKitManager.e().a(ppk.h());
            }
        });
    }

    public static void a() {
        LogUtil.a("SleepNotificationHelper", "closeNotification");
        jdh.c().a(20210816);
    }

    private static List<PendingIntent> c(Context context) {
        ArrayList arrayList = new ArrayList();
        Intent intent = new Intent(context, (Class<?>) HealthCommondReceiver.class);
        intent.setAction("start");
        intent.setPackage(BaseApplication.d());
        intent.putExtra("timeZoneId", TimeZone.getDefault().getID());
        arrayList.add(PendingIntent.getBroadcast(context, 1, intent, 201326592));
        Intent intent2 = new Intent(context, (Class<?>) HealthCommondReceiver.class);
        intent2.setAction("end");
        intent2.setPackage(BaseApplication.d());
        intent2.putExtra("timeZoneId", TimeZone.getDefault().getID());
        arrayList.add(PendingIntent.getBroadcast(context, 2, intent2, 201326592));
        return arrayList;
    }

    public static void d(Context context) {
        RemoteViews remoteViews;
        LogUtil.a("SleepNotificationHelper", "showNotification");
        if (e()) {
            return;
        }
        if (!n()) {
            LogUtil.h("SleepNotificationHelper", "It's not a reminder time.");
            return;
        }
        b(System.currentTimeMillis());
        if (EnvironmentInfo.i() || CommonUtil.bm()) {
            LogUtil.a("SleepNotificationHelper", "showNotification isHarmony4AndLater");
            b(context);
            return;
        }
        if (BaseActivity.isMiui()) {
            remoteViews = new RemoteViews(context.getPackageName(), R.layout.layout_sleep_notification_xiaomi);
        } else if (Build.VERSION.SDK_INT >= 31) {
            remoteViews = new RemoteViews(context.getPackageName(), R.layout.layout_sleep_notification_tar_31);
        } else {
            remoteViews = new RemoteViews(context.getPackageName(), R.layout.layout_sleep_notification);
        }
        if (LanguageUtil.bc(context)) {
            remoteViews.setImageViewBitmap(R.id.right_icon, nrz.cKn_(context, R.mipmap._2131821059_res_0x7f110203).getBitmap());
        }
        Intent intent = new Intent(context, (Class<?>) HealthCommondReceiver.class);
        intent.setAction("endTheDay");
        intent.setPackage(BaseApplication.d());
        remoteViews.setOnClickPendingIntent(R.id.notify_close, PendingIntent.getBroadcast(context, 2, intent, 201326592));
        remoteViews.setOnClickPendingIntent(R.id.start_sleep, PendingIntent.getActivity(context, 0, new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("huaweischeme://healthapp/basicHealth?healthType=1&pageType=detection&isStart=true&fromType=1&pullTime=" + System.currentTimeMillis())), AppRouterExtras.COLDSTART));
        remoteViews.setImageViewResource(R.id.icon, R.drawable.healthlogo_ic_notification);
        String string = context.getResources().getString(R$string.IDS_app_name_health);
        String upperCase = context.getResources().getString(R$string.IDS_sleep_start).toUpperCase();
        remoteViews.setTextViewText(R.id.app_name_text, string);
        remoteViews.setTextViewText(R.id.textContent, i());
        remoteViews.setTextViewText(R.id.right_icon_text, upperCase);
        Notification build = jdh.c().xf_().setSmallIcon(R.drawable.healthlogo_ic_notification).setWhen(System.currentTimeMillis()).setShowWhen(false).setVisibility(1).setPriority(1).setOngoing(true).setContentIntent(PendingIntent.getActivity(context, 0, new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("huaweischeme://healthapp/basicHealth?healthType=1&pageType=detection&fromType=1")), AppRouterExtras.COLDSTART)).build();
        build.contentView = remoteViews;
        jdh.c().xh_(20210816, build);
    }

    private static void b(Context context) {
        new Intent(context, (Class<?>) HealthCommondReceiver.class).setAction("endTheDay");
        Notification.Builder style = jdh.c().xf_().setSmallIcon(R.drawable.healthlogo_ic_notification).setContentTitle(context.getResources().getString(R$string.IDS_intel_record_sleep)).setContentText(context.getResources().getString(R$string.IDS_sleep_notification_2)).setStyle(new Notification.BigTextStyle()).setWhen(System.currentTimeMillis()).setShowWhen(false).setVisibility(1).setPriority(1).setOngoing(true).setContentIntent(PendingIntent.getActivity(context, 0, new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("huaweischeme://healthapp/basicHealth?healthType=1&pageType=detection&fromType=1")), AppRouterExtras.COLDSTART)).setStyle(new Notification.BigTextStyle());
        style.addAction(new Notification.Action.Builder((Icon) null, context.getResources().getString(R$string.IDS_sleep_start), PendingIntent.getActivity(context, 0, new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("huaweischeme://healthapp/basicHealth?healthType=1&pageType=detection&isStart=true&fromType=1&pullTime=" + System.currentTimeMillis())), AppRouterExtras.COLDSTART)).build());
        jdh.c().xh_(20210816, style.build());
    }

    private static String i() {
        Context e = BaseApplication.e();
        int nextInt = new SecureRandom().nextInt(4);
        if (nextInt == 0) {
            return e.getString(R$string.IDS_sleep_notification_1);
        }
        if (nextInt == 1) {
            return e.getString(R$string.IDS_sleep_notification_2);
        }
        if (nextInt == 2) {
            return e.getString(R$string.IDS_sleep_notification_3);
        }
        if (nextInt == 3) {
            return e.getString(R$string.IDS_sleep_notification_4);
        }
        return e.getString(R$string.IDS_sleep_notification_1);
    }

    public static boolean e() {
        String b = SharedPreferenceManager.b(BaseApplication.e(), Integer.toString(10000), "SLEEP_NOTIFY_CLOSE_TIME");
        LogUtil.a("SleepNotificationHelper", "isTodayClose closeTime: ", b);
        boolean c = c(CommonUtil.g(b), System.currentTimeMillis());
        LogUtil.a("SleepNotificationHelper", "isTodayClose: ", Boolean.valueOf(c));
        return c;
    }

    public static void b(long j) {
        LogUtil.a("SleepNotificationHelper", "setCloseTime: ", Long.valueOf(j));
        SharedPreferenceManager.e(BaseApplication.e(), Integer.toString(10000), "SLEEP_NOTIFY_CLOSE_TIME", String.valueOf(j), (StorageParams) null);
    }

    private static boolean c(long j, long j2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(j2);
        return calendar.get(1) == calendar2.get(1) && calendar.get(2) == calendar2.get(2) && calendar.get(5) == calendar2.get(5);
    }

    private static List<String> h() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("start");
        arrayList.add("end");
        return arrayList;
    }

    private static boolean n() {
        long currentTimeMillis = System.currentTimeMillis();
        int sleepTime = mtp.d().getSleepTime();
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, sleepTime / 60);
        calendar.set(12, (sleepTime % 60) - 30);
        calendar.set(13, 0);
        calendar.set(14, 0);
        long timeInMillis = calendar.getTimeInMillis();
        return currentTimeMillis >= timeInMillis && currentTimeMillis <= timeInMillis + 10800000;
    }
}
