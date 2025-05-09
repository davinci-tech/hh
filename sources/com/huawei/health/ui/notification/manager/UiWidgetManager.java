package com.huawei.health.ui.notification.manager;

import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import com.huawei.health.manager.util.Consts;
import com.huawei.health.ui.notification.constants.UiComponent;
import com.huawei.health.ui.widget.HealthSportWidget;
import com.huawei.health.ui.widget.HonorWidgetProvider;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.AsyncInvocationHandler;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.UiHandler;
import java.lang.reflect.Proxy;

/* loaded from: classes.dex */
public class UiWidgetManager implements IUiWidgetManager {

    /* renamed from: a, reason: collision with root package name */
    private d f3474a;
    private Handler d;
    private UiHandler e = null;

    public UiWidgetManager(Context context) {
        this.f3474a = null;
        this.d = null;
        this.f3474a = new d();
        LogUtil.a("Step_UiWidgetManager", "registerDynamicBroadcastReceiver");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.health.WIDGET_ENABLE");
        intentFilter.addAction("com.huawei.health.WIDGET_DISABLE");
        intentFilter.addAction("com.huawei.health.WIDGET_FORCE_UPDATE");
        intentFilter.addAction("android.intent.action.CONFIGURATION_CHANGED");
        intentFilter.addAction("android.intent.action.WALLPAPER_CHANGED");
        if (context != null) {
            BroadcastManagerUtil.bFA_(context, this.f3474a, intentFilter, Consts.f2803a, null);
        }
        this.d = new Handler();
    }

    public IUiWidgetManager d() {
        Object newProxyInstance = Proxy.newProxyInstance(getClass().getClassLoader(), getClass().getInterfaces(), new AsyncInvocationHandler(this));
        if (newProxyInstance instanceof IUiWidgetManager) {
            return (IUiWidgetManager) newProxyInstance;
        }
        return null;
    }

    public void b(UiHandler uiHandler) {
        if (uiHandler == null) {
            LogUtil.b("Step_UiWidgetManager", "uiHandler is null");
        } else {
            this.e = uiHandler;
        }
    }

    @Override // com.huawei.health.ui.notification.manager.IUiWidgetManager
    public void enableWidget() {
        this.e.b(UiComponent.WIDGET);
        this.e.b();
    }

    @Override // com.huawei.health.ui.notification.manager.IUiWidgetManager
    public void disableWidget() {
        this.e.c(UiComponent.WIDGET);
    }

    @Override // com.huawei.health.ui.notification.manager.IUiWidgetManager
    public void forceUpdate() {
        this.e.b();
    }

    @Override // com.huawei.health.ui.notification.manager.IUiWidgetManager
    public void wallPaperChanged() {
        int i = 0;
        for (int i2 = 0; i2 < 3; i2++) {
            i += 1000;
            this.d.postDelayed(new Runnable() { // from class: com.huawei.health.ui.notification.manager.UiWidgetManager.4
                @Override // java.lang.Runnable
                public void run() {
                    UiWidgetManager.this.e.c(UiComponent.WIDGET);
                    UiWidgetManager.this.e.b(UiComponent.WIDGET);
                    UiWidgetManager.this.e.b();
                }
            }, i);
        }
    }

    class d extends BroadcastReceiver {
        private d() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action;
            if (intent == null || (action = intent.getAction()) == null) {
                return;
            }
            IUiWidgetManager d = UiWidgetManager.this.d();
            if (d == null) {
                LogUtil.h("Step_UiWidgetManager", "widgetManager is null");
                return;
            }
            if (action.equals("com.huawei.health.WIDGET_ENABLE")) {
                d.enableWidget();
                return;
            }
            if (action.equals("com.huawei.health.WIDGET_DISABLE")) {
                d.disableWidget();
                return;
            }
            if (action.equals("com.huawei.health.WIDGET_FORCE_UPDATE")) {
                d.forceUpdate();
                return;
            }
            if (action.equals("android.intent.action.CONFIGURATION_CHANGED")) {
                d.forceUpdate();
            } else if (action.equals("android.intent.action.WALLPAPER_CHANGED")) {
                d.wallPaperChanged();
            } else {
                LogUtil.a("Step_UiWidgetManager", "BroadcastReceiver no action to do");
            }
        }
    }

    public static boolean d(Context context) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, (Class<?>) HealthSportWidget.class));
        int[] appWidgetIds2 = appWidgetManager.getAppWidgetIds(new ComponentName(context, (Class<?>) HonorWidgetProvider.class));
        return (appWidgetIds == null && appWidgetIds2 == null) || ((appWidgetIds != null && appWidgetIds.length == 0) && (appWidgetIds2 != null && appWidgetIds2.length == 0));
    }
}
