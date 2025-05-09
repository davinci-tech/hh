package com.huawei.health.ui.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.health.R;
import com.huawei.health.manager.DaemonService;
import com.huawei.health.manager.util.Consts;
import com.huawei.health.ui.notification.manager.UiWidgetManager;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.NotificationUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
public class HealthSportWidget extends AppWidgetProvider {

    /* renamed from: a, reason: collision with root package name */
    private boolean f3485a = false;
    private static AtomicBoolean e = new AtomicBoolean(true);
    private static c b = new c();
    private static Bundle c = new Bundle();
    private static long d = 0;

    @Override // android.appwidget.AppWidgetProvider
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] iArr) {
        super.onUpdate(context, appWidgetManager, iArr);
        if (context != null) {
            if (!this.f3485a) {
                LogUtil.a("Step_HealthSportWid", "onUpdate():  start DaemonService");
                Intent intent = new Intent(context, (Class<?>) DaemonService.class);
                intent.setPackage(context.getPackageName());
                try {
                    context.startService(intent);
                } catch (IllegalStateException e2) {
                    LogUtil.b("Step_HealthSportWid", "healthSportWidget onUpdate", e2.getMessage());
                }
                this.f3485a = true;
            }
            c(context);
            synchronized (HealthSportWidget.class) {
                aOB_(context, c);
            }
        }
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onEnabled(Context context) {
        if (context != null) {
            super.onEnabled(context);
            LogUtil.a("Step_HealthSportWid", "onEnabled");
            Intent intent = new Intent("com.huawei.health.WIDGET_ENABLE");
            intent.setPackage(context.getPackageName());
            context.sendBroadcast(intent, Consts.f2803a);
        }
        synchronized (HealthSportWidget.class) {
            aOB_(context, c);
        }
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onDisabled(Context context) {
        super.onDisabled(context);
        LogUtil.a("Step_HealthSportWid", "onDisabled");
        if (context == null || !UiWidgetManager.d(context)) {
            return;
        }
        Intent intent = new Intent("com.huawei.health.WIDGET_DISABLE");
        intent.setPackage(context.getPackageName());
        context.sendBroadcast(intent, Consts.f2803a);
    }

    @Override // android.appwidget.AppWidgetProvider, android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null || context == null) {
            ReleaseLogUtil.d("Step_HealthSportWid", "intent or context is null");
            return;
        }
        super.onReceive(context, intent);
        String action = intent.getAction();
        if (action == null || !"android.intent.action.LOCALE_CHANGED".equals(action)) {
            return;
        }
        LogUtil.c("Step_HealthSportWid", "ACTION_LOCALE_CHANGED");
        d(context, true);
        c(context);
    }

    private static void d(Context context, boolean z) {
        c cVar;
        synchronized (c.class) {
            cVar = b;
        }
        b(context, cVar, z);
    }

    private static void e(Context context, c cVar) {
        b(context, cVar, false);
    }

    public static void aOB_(Context context, Bundle bundle) {
        synchronized (HealthSportWidget.class) {
            if (bundle != null && context != null) {
                c = bundle;
                if (UiWidgetManager.d(context)) {
                    LogUtil.a("Step_HealthSportWid", "Without Widget!");
                    Intent intent = new Intent("com.huawei.health.WIDGET_DISABLE");
                    intent.setPackage(context.getPackageName());
                    context.sendBroadcast(intent, Consts.f2803a);
                } else {
                    a aVar = new a();
                    aVar.c = bundle.getInt("step", 0);
                    aVar.b = bundle.getInt("distance", 0);
                    aVar.f3486a = bundle.getInt("carior", 0) / 1000.0f;
                    aVar.e = bundle.getInt("target");
                    aVar.d = bundle.getInt("KEY_BG_COLOR_TYPE");
                    AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
                    aOA_(context, appWidgetManager, appWidgetManager.getAppWidgetIds(new ComponentName(context, (Class<?>) HealthSportWidget.class)), aVar, 1);
                }
            }
        }
    }

    static class a {

        /* renamed from: a, reason: collision with root package name */
        private float f3486a;
        private int b;
        private int c;
        private int d;
        private int e;

        private a() {
            this.c = 0;
            this.b = 0;
        }
    }

    public static void c(Context context) {
        LogUtil.a("Step_HealthSportWid", "sendForceUpdateBroadcast");
        if (context != null) {
            Intent intent = new Intent("com.huawei.health.WIDGET_FORCE_UPDATE");
            intent.setPackage(context.getPackageName());
            context.sendBroadcast(intent, Consts.f2803a);
        }
    }

    private static void aOA_(Context context, AppWidgetManager appWidgetManager, int[] iArr, a aVar, int i) {
        if (aVar == null || appWidgetManager == null) {
            ReleaseLogUtil.c("Step_HealthSportWid", "definedData or appWidgetManager is null");
            return;
        }
        c a2 = a(aVar);
        e(context, a2);
        RemoteViews aOz_ = aOz_(context, a2);
        Intent aOy_ = aOy_(context);
        aOy_.putExtra("mLaunchSource", 13);
        aOz_.setOnClickPendingIntent(R.id.widget_4x1_LinearLayout, PendingIntent.getActivity(context, 13, aOy_, AppRouterExtras.COLDSTART));
        appWidgetManager.updateAppWidget(iArr, aOz_);
    }

    private static boolean a(int i, int i2) {
        return Integer.toString(i).length() == Integer.toString(i2).length();
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0069 A[Catch: all -> 0x0101, TryCatch #0 {, blocks: (B:6:0x0009, B:8:0x001b, B:11:0x002c, B:16:0x0069, B:17:0x0074, B:38:0x003f), top: B:5:0x0009 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void b(android.content.Context r6, com.huawei.health.ui.widget.HealthSportWidget.c r7, boolean r8) {
        /*
            Method dump skipped, instructions count: 272
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.ui.widget.HealthSportWidget.b(android.content.Context, com.huawei.health.ui.widget.HealthSportWidget$c, boolean):void");
    }

    private static RemoteViews aOz_(Context context, c cVar) {
        RemoteViews remoteViews;
        if (cVar == null) {
            LogUtil.c("Step_HealthSportWid", "widgetData is null");
            return null;
        }
        if (context.getResources().getConfiguration().orientation == 1) {
            if (e.get()) {
                LogUtil.c("Step_HealthSportWid", "remoteViews is widget4x1");
                remoteViews = new RemoteViews(context.getPackageName(), R.layout.hw_healthcloud_widget4x1);
            } else {
                LogUtil.c("Step_HealthSportWid", "remoteViews is widget4x1_adapter");
                remoteViews = new RemoteViews(context.getPackageName(), R.layout.hw_healthcloud_widget4x1_adapter);
            }
        } else {
            LogUtil.c("Step_HealthSportWid", "Orientation is not ORIENTATION_PORTRAIT");
            remoteViews = new RemoteViews(context.getPackageName(), R.layout.hw_healthcloud_widget4x1);
        }
        remoteViews.setImageViewResource(R.id.split, cVar.e);
        remoteViews.setTextColor(R.id.widget4x1_steps, cVar.f3487a);
        remoteViews.setTextColor(R.id.widget4x1_steps_unit, cVar.f3487a);
        remoteViews.setTextColor(R.id.widget4x1_stepGoal_prefix, cVar.f3487a);
        remoteViews.setTextColor(R.id.widget4x1_stepGoal, cVar.f3487a);
        remoteViews.setTextColor(R.id.widget4x1_kcal_prefix, cVar.f3487a);
        remoteViews.setTextColor(R.id.widget4x1_kcal, cVar.f3487a);
        remoteViews.setTextColor(R.id.widget4x1_kcal_unit, cVar.f3487a);
        aOE_(context, remoteViews, R.id.widget4x1_steps, cVar.b);
        aOD_(context, remoteViews, R.id.widget4x1_stepGoal, cVar.d);
        aOC_(context, remoteViews, R.id.widget4x1_kcal, cVar.c);
        return remoteViews;
    }

    private static int e(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    static class c {

        /* renamed from: a, reason: collision with root package name */
        private int f3487a;
        private int b;
        private int c;
        private int d;
        private int e;

        private c() {
            this.b = 0;
            this.d = 0;
            this.c = 0;
        }
    }

    private static c a(a aVar) {
        int i;
        c cVar = new c();
        if (aVar == null) {
            ReleaseLogUtil.c("Step_HealthSportWid", "definedData is null");
            return cVar;
        }
        long b2 = LogUtil.b(5000, d);
        if (b2 != -1) {
            ReleaseLogUtil.e("Step_HealthSportWid", " Enter GetWidgetData step: ", Integer.valueOf(aVar.c), " distance: ", Integer.valueOf(aVar.b));
            d = b2;
        }
        if (aVar.d != 1) {
            if (aVar.d == 2) {
                cVar.e = R.drawable._2131428735_res_0x7f0b057f;
            } else {
                LogUtil.c("Step_HealthSportWid", "this devices not support widget bg recognized");
                cVar.e = R.drawable._2131428735_res_0x7f0b057f;
            }
            i = -1;
        } else {
            cVar.e = R.drawable._2131428734_res_0x7f0b057e;
            i = -16777216;
        }
        cVar.f3487a = i;
        if (aVar.c != -1) {
            cVar.b = aVar.c;
        }
        if (aVar.e != -1) {
            if (aVar.e == 0) {
                ReleaseLogUtil.d("Step_HealthSportWid", "Division by zero attempted!");
                return cVar;
            }
            e(aVar, cVar);
        }
        if (aVar.f3486a != -1.0f) {
            cVar.c = Math.round(aVar.f3486a);
        }
        return cVar;
    }

    private static void e(a aVar, c cVar) {
        int round = (int) Math.round((aVar.c / aVar.e) * 100.0d);
        if (round == 100 && aVar.c < aVar.e) {
            round = 99;
        } else if (round > 100) {
            round = 100;
        } else {
            LogUtil.c("Step_HealthSportWid", "completedGoal is no match");
        }
        LogUtil.c("Step_HealthSportWid", "mCompletedGoal : ", Integer.valueOf(round));
        cVar.d = round;
    }

    private static Intent aOy_(Context context) {
        Intent launchIntentForPackage;
        Intent intent = new Intent();
        try {
            launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        } catch (UnsupportedOperationException e2) {
            LogUtil.b("Step_HealthSportWid", "getHealthAPPIntent()", e2.getMessage());
        }
        if (launchIntentForPackage == null) {
            ReleaseLogUtil.d("Step_HealthSportWid", "getLaunchIntentForPackage return null,set no action!!!");
            return intent;
        }
        intent.setComponent(new ComponentName(context.getPackageName(), launchIntentForPackage.getComponent().getClassName()));
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setFlags(268435456);
        return intent;
    }

    private static void aOE_(Context context, RemoteViews remoteViews, int i, int i2) {
        if (remoteViews != null) {
            remoteViews.setTextViewText(i, NotificationUtil.d(context, i2));
        } else {
            LogUtil.b("Step_HealthSportWid", "There have a null remoteView ");
        }
    }

    private static void aOD_(Context context, RemoteViews remoteViews, int i, int i2) {
        if (remoteViews != null) {
            remoteViews.setTextViewText(i, NotificationUtil.c(context, i2));
        } else {
            LogUtil.b("Step_HealthSportWid", "There have a null remoteView ");
        }
    }

    private static void aOC_(Context context, RemoteViews remoteViews, int i, int i2) {
        if (remoteViews != null) {
            remoteViews.setTextViewText(i, NotificationUtil.b(context, i2));
        } else {
            LogUtil.b("Step_HealthSportWid", "There have a null remoteView ");
        }
    }
}
