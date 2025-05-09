package com.huawei.health.ui.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.widget.RemoteViews;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.health.R;
import com.huawei.health.manager.DaemonService;
import com.huawei.health.manager.util.Consts;
import com.huawei.health.ui.notification.manager.UiWidgetManager;
import com.huawei.health.ui.notification.utils.CircleProcessUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.NotificationUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes.dex */
public class HonorWidgetProvider extends AppWidgetProvider {
    private static Bundle c = new Bundle();
    private boolean b;
    private boolean e = false;

    @Override // android.appwidget.AppWidgetProvider
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] iArr) {
        super.onUpdate(context, appWidgetManager, iArr);
        if (context != null) {
            LogUtil.a("Step_HonorWidProv", "onUpdate");
            if (!this.e) {
                LogUtil.a("Step_HonorWidProv", "onUpdate(): start DaemonService");
                Intent intent = new Intent(context, (Class<?>) DaemonService.class);
                intent.setPackage(context.getPackageName());
                try {
                    context.startService(intent);
                } catch (IllegalStateException e) {
                    LogUtil.b("Step_HonorWidProv", "HonorWidgetProvider onUpdate", e.getMessage());
                }
                this.e = true;
            }
            c(context);
            synchronized (HonorWidgetProvider.class) {
                if (c.getInt("step", 0) != 0 || !this.b) {
                    this.b = true;
                    aOI_(context, c);
                }
            }
        }
    }

    private static void aOH_(Context context, AppWidgetManager appWidgetManager, int[] iArr, d dVar) {
        if (dVar == null || appWidgetManager == null) {
            LogUtil.h("Step_HonorWidProv", "onUpdate widgetRefreshData or appWidgetManager is null");
        } else {
            appWidgetManager.updateAppWidget(iArr, aOG_(context, e(dVar)));
        }
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onEnabled(Context context) {
        if (context != null) {
            super.onEnabled(context);
            LogUtil.a("Step_HonorWidProv", "onEnabled");
            Intent intent = new Intent("com.huawei.health.WIDGET_ENABLE");
            intent.setPackage(context.getPackageName());
            context.sendBroadcast(intent, Consts.f2803a);
        }
        synchronized (HonorWidgetProvider.class) {
            if (c.getInt("step", 0) != 0 || !this.b) {
                this.b = true;
                aOI_(context, c);
            }
        }
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onDisabled(Context context) {
        super.onDisabled(context);
        LogUtil.a("Step_HonorWidProv", "onDisabled");
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
            LogUtil.h("Step_HonorWidProv", "intent or context is null");
            return;
        }
        super.onReceive(context, intent);
        String action = intent.getAction();
        if (action == null || !"android.intent.action.LOCALE_CHANGED".equals(action)) {
            return;
        }
        LogUtil.a("Step_HonorWidProv", "ACTION_LOCALE_CHANGED");
        c(context);
    }

    public static void aOI_(Context context, Bundle bundle) {
        synchronized (HonorWidgetProvider.class) {
            if (bundle != null && context != null) {
                c = bundle;
                if (UiWidgetManager.d(context)) {
                    LogUtil.a("Step_HonorWidProv", "Without Widget!");
                    Intent intent = new Intent("com.huawei.health.WIDGET_DISABLE");
                    intent.setPackage(context.getPackageName());
                    context.sendBroadcast(intent, Consts.f2803a);
                } else {
                    d dVar = new d();
                    dVar.d = bundle.getInt("step", 0);
                    dVar.b = bundle.getInt("target", 0);
                    dVar.e = bundle.getInt("distance", 0) / 1000.0f;
                    dVar.c = bundle.getInt("carior", 0) / 1000.0f;
                    AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
                    aOH_(context, appWidgetManager, appWidgetManager.getAppWidgetIds(new ComponentName(context, (Class<?>) HonorWidgetProvider.class)), dVar);
                }
            }
        }
    }

    public static void c(Context context) {
        LogUtil.a("Step_HonorWidProv", "sendForceUpdateBroadcast");
        if (context != null) {
            Intent intent = new Intent("com.huawei.health.WIDGET_FORCE_UPDATE");
            intent.setPackage(context.getPackageName());
            context.sendBroadcast(intent, Consts.f2803a);
        }
    }

    private static RemoteViews aOG_(Context context, c cVar) {
        RemoteViews remoteViews;
        if (cVar == null) {
            LogUtil.a("Step_HonorWidProv", "getWidgetRemoteViews honorWidgetData is null");
            return null;
        }
        boolean z = false;
        PendingIntent activity = PendingIntent.getActivity(context, 0, aOF_(context), AppRouterExtras.COLDSTART);
        if (CommonUtil.bj()) {
            remoteViews = new RemoteViews(context.getPackageName(), R.layout.layout_honor_widget_adapter);
        } else {
            remoteViews = new RemoteViews(context.getPackageName(), R.layout.layout_honor_widget);
        }
        remoteViews.setOnClickPendingIntent(R.id.honor_widget_rl, activity);
        if (String.valueOf(cVar.d).length() >= 4 && !LanguageUtil.j(context)) {
            z = true;
        }
        if (String.valueOf(cVar.d).length() >= 5 || z) {
            remoteViews.setTextViewTextSize(R.id.widget2x2_steps, 1, 18.0f);
        }
        remoteViews.setTextViewText(R.id.widget2x2_steps, NotificationUtil.d(context, cVar.d));
        remoteViews.setTextViewText(R.id.widget2x2_goal_progress, NotificationUtil.c(context, cVar.c));
        remoteViews.setTextViewText(R.id.widget2x2_calories, NotificationUtil.b(context, cVar.b));
        aOJ_(remoteViews, R.plurals.IDS_plugindameon_widget_distance_km_unit, (int) cVar.e, NotificationUtil.d(context, cVar.e));
        remoteViews.setImageViewBitmap(R.id.widget2x2_goal_progress_iv, new CircleProcessUtil(context).aOu_(cVar.c / 100.0f));
        LogUtil.a("Step_HonorWidProv", "getWidgetRemoteViews mCompletedGoal=  ", Float.valueOf(cVar.c / 100.0f));
        if (LanguageUtil.bi(context) && Build.VERSION.SDK_INT > 33) {
            remoteViews.setViewLayoutMargin(R.id.widget2x2_layout_middle, 1, 40.0f, 1);
            remoteViews.setViewLayoutMargin(R.id.widget2x2_layout_middle, 3, 0.0f, 1);
            remoteViews.setViewLayoutMargin(R.id.widget2x2_goal_progress, 3, 8.0f, 1);
            remoteViews.setViewLayoutMargin(R.id.widget2x2_layout_bottom, 3, 10.0f, 1);
            remoteViews.setViewLayoutMargin(R.id.widget2x2_calories, 3, 5.0f, 1);
            remoteViews.setTextViewTextSize(R.id.widget2x2_steps, 1, 22.0f);
        }
        return remoteViews;
    }

    private static void aOJ_(RemoteViews remoteViews, int i, int i2, String str) {
        LogUtil.a("Step_HonorWidProv", "setDistanceText() ");
        String quantityString = BaseApplication.getContext().getResources().getQuantityString(i, i2, str);
        int length = str.length();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(quantityString);
        if (!quantityString.substring(0, length).equals(str)) {
            length = spannableStringBuilder.length() - length;
        }
        spannableStringBuilder.setSpan(new ForegroundColorSpan(BaseApplication.getContext().getColor(R.color._2131296864_res_0x7f090260)), 0, length, 17);
        spannableStringBuilder.setSpan(new AbsoluteSizeSpan(12, true), 0, length, 17);
        remoteViews.setTextViewText(R.id.widget2x2_distance, spannableStringBuilder);
    }

    private static c e(d dVar) {
        c cVar = new c();
        ReleaseLogUtil.e("Step_HonorWidProv", " Enter getWidgetData step: ", Integer.valueOf(dVar.d), " distance: ", Float.valueOf(dVar.e), " mStepGoal: ", Integer.valueOf(dVar.b));
        if (dVar.d < 0) {
            ReleaseLogUtil.d("Step_HonorWidProv", "getWidgetData error widgetRefreshData.mStep = ", Integer.valueOf(dVar.d));
            return cVar;
        }
        cVar.d = dVar.d;
        cVar.b = Math.round(dVar.c);
        cVar.e = dVar.e;
        if (dVar.b <= 0) {
            ReleaseLogUtil.d("Step_HonorWidProv", "getWidgetData error mStepGoal = 0 ");
            return cVar;
        }
        int round = (int) Math.round((dVar.d / dVar.b) * 100.0d);
        if (round == 100 && dVar.d < dVar.b) {
            round = 99;
        } else if (round > 100) {
            round = 100;
        } else {
            ReleaseLogUtil.e("Step_HonorWidProv", "getWidgetData mCompletedGoal : ", Integer.valueOf(round));
        }
        cVar.c = round;
        return cVar;
    }

    private static Intent aOF_(Context context) {
        Intent launchIntentForPackage;
        Intent intent = new Intent();
        try {
            launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        } catch (UnsupportedOperationException e) {
            LogUtil.b("Step_HonorWidProv", "getHealthAPPIntent()", e.getMessage());
        }
        if (launchIntentForPackage == null) {
            ReleaseLogUtil.d("Step_HonorWidProv", "getLaunchIntentForPackage return null,set no action!!!");
            return intent;
        }
        intent.setComponent(new ComponentName(context.getPackageName(), launchIntentForPackage.getComponent().getClassName()));
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setFlags(268435456);
        SharedPreferenceManager.e(context, Integer.toString(10000), "honor_widget", "true", new StorageParams());
        return intent;
    }

    static class d {
        private int b;
        private float c;
        private int d;
        private float e;

        private d() {
        }
    }

    static class c {
        private int b;
        private int c;
        private int d;
        private float e;

        private c() {
            this.d = 0;
            this.c = 0;
            this.b = 0;
            this.e = 0.0f;
        }
    }
}
