package com.huawei.hianalytics.visual;

import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.process.HiAnalyticsConfig;
import com.huawei.hianalytics.process.HiAnalyticsInstance;
import com.huawei.hianalytics.process.HiAnalyticsManager;
import com.huawei.hianalytics.visual.HAAutoConfigOptions;
import com.huawei.hianalytics.visual.autocollect.EventType;
import com.huawei.hianalytics.visual.autocollect.exposure.ViewMark;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class HiAnalyticsAPI extends HiAnalyticsManager {
    public static final String HA_VISUAL_SDK_VERSION = "3.2.12.500";
    public static final String TAG = "HiAnalyticsAPI";
    public static boolean hasInit = false;
    public static volatile HiAnalyticsAPI hiAnalyticsAPI;

    public static HiAnalyticsAPI getInstance() {
        if (hiAnalyticsAPI == null) {
            synchronized (HiAnalyticsAPI.class) {
                if (hiAnalyticsAPI == null) {
                    hiAnalyticsAPI = new HiAnalyticsAPI();
                }
            }
        }
        return hiAnalyticsAPI;
    }

    public String acquireSessionID() {
        return b.a().e();
    }

    public void addAutoCollectVisualActivities(List<Class<?>> list) {
        b.a().d(list);
    }

    public void addAutoCollectVisualActivity(Class<?> cls) {
        b.a().e(cls);
    }

    public void addSuperProperties(JSONObject jSONObject) {
        b.a().a(jSONObject);
    }

    public void addViewActivity(View view, Activity activity) {
        b.a().a(view, activity);
    }

    public void addViewCustomProperties(View view, JSONObject jSONObject) {
        b.a().a(view, jSONObject);
    }

    public void addViewFragment(View view, String str) {
        b.a().a(view, str);
    }

    public void addViewId(View view, String str) {
        b.a().b(view, str);
    }

    @Deprecated
    public void collectAppFirstOpen() {
    }

    @Deprecated
    public void collectAppFirstOpen(String str, JSONObject jSONObject) {
    }

    @Deprecated
    public void collectAppFirstOpen(JSONObject jSONObject) {
    }

    public void deleteSuperProperties() {
        b.a().h();
    }

    public void disableAutoCollectActivities(List<Class<?>> list) {
        b.a().g(list);
    }

    public void disableAutoCollectActivity(Class<?> cls) {
        b.a().a(cls);
    }

    public void disableAutoCollectEvent(List<EventType> list) {
        b.a().c(list);
    }

    public void disableAutoCollectFragment(Class<?> cls) {
        b.a().d(cls);
    }

    public void disableAutoCollectFragments(List<Class<?>> list) {
        b.a().a(list);
    }

    public void disableCollectView(View view) {
        b.a().a(view);
    }

    public void disableViewType(Class<?> cls) {
        b.a().b(cls);
    }

    public void enableAutoCollect(boolean z) {
        b.a().a(z);
    }

    public void enableAutoCollectActivities(List<Class<?>> list) {
        b.a().b(list);
    }

    public void enableAutoCollectActivity(Class<?> cls) {
        b.a().c(cls);
    }

    public void enableAutoCollectEvent(List<EventType> list) {
        b.a().f(list);
    }

    public void enableAutoCollectFragment(Class<?> cls) {
        b.a().f(cls);
    }

    public void enableAutoCollectFragments(List<Class<?>> list) {
        b.a().h(list);
    }

    public void enableAutoCollectPackages(List<String> list) {
        b.a().e(list);
    }

    public HiAnalyticsInstance getAutoHiAnalyticsInstance() {
        if (hasInit) {
            return HiAnalyticsManager.getInstanceByTag("hianalytics_default_autocollect");
        }
        HiLog.sw(TAG, "auto hiAnalytics instance has not initialized, please init");
        return null;
    }

    public void markViewExposure(ViewMark viewMark) {
        b.a().a(viewMark);
    }

    public void onReport() {
        b.a().d();
    }

    @Deprecated
    public void preInitAutoCollect(Context context) {
        HiLog.d(TAG, "HiAnalytics Visual auto collect pre init start");
        if (context == null) {
            HiLog.w(TAG, "HiAnalytics Visual auto collect pre init failed, context is null");
            return;
        }
        Application application = (Application) context.getApplicationContext();
        if (application == null) {
            HiLog.w(TAG, "HiAnalytics Visual auto collect pre init failed, application context is null");
            return;
        }
        v vVar = v.c;
        synchronized (vVar) {
            if (vVar.b == null) {
                vVar.b = new q();
                application.registerActivityLifecycleCallbacks(vVar.b);
            }
        }
        HiLog.d(TAG, "HiAnalytics Visual auto collect pre init end");
    }

    public void removePresetProperty(String str) {
        b.a().b(str);
    }

    public void removeSuperProperties(String str) {
        b.a().a(str);
    }

    public void startAutoCollect(Context context, HiAnalyticsConfig hiAnalyticsConfig, HAAutoConfigOptions hAAutoConfigOptions) {
        startAutoCollect(context, hiAnalyticsConfig, null, hAAutoConfigOptions);
    }

    public void stopViewExposure(View view) {
        b.a().b(view);
    }

    public void syncVisualConfig() {
        b.a().f();
    }

    public void addViewId(Dialog dialog, String str) {
        b.a().a(dialog, str);
    }

    public void removePresetProperty(String str, List<String> list) {
        b.a().a(str, list);
    }

    public void startAutoCollect(Context context, HiAnalyticsConfig hiAnalyticsConfig, HiAnalyticsConfig hiAnalyticsConfig2, HAAutoConfigOptions hAAutoConfigOptions) {
        HiLog.i(TAG, "HiAnalytics Visual auto collect instance init start");
        if (hasInit) {
            HiLog.sw(TAG, "startAutoCollect(): auto instance has already been initialized");
            return;
        }
        if (hAAutoConfigOptions == null) {
            hAAutoConfigOptions = new HAAutoConfigOptions.Builder().build();
        }
        try {
            d.a(context, hiAnalyticsConfig, hiAnalyticsConfig2, hAAutoConfigOptions);
            hasInit = true;
        } catch (Throwable th) {
            HiLog.sw(TAG, "error occurred while initialize HiAnalytics Agent: " + th.getMessage());
        }
    }

    public void addViewId(Object obj, String str) {
        b.a().a(obj, str);
    }
}
