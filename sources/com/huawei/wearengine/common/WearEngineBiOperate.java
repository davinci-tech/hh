package com.huawei.wearengine.common;

import android.content.Context;
import defpackage.tos;
import defpackage.tri;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes7.dex */
public class WearEngineBiOperate {
    private static final String BI_ANALYTICS_UTIL = "com.huawei.wearengine.utills.WearEngineReflectUtil";
    private static final int DEFAULT_MAP_SIZE = 16;
    private static final String KEY_API_NAME = "apiName";
    private static final String KEY_APP_ID = "appID";
    private static final String KEY_CALL_TIME = "callTime";
    private static final String KEY_COST_TIME = "costTime";
    private static final String KEY_COUNTRY = "country";
    private static final String KEY_PACKAGE = "package";
    private static final String KEY_RESULT = "result";
    private static final String KEY_SDK_VERSION = "version";
    private static final String KEY_SERVICE = "service";
    private static final String KEY_SERVICE_VERSION = "appVersion";
    private static final String SERVICE_VALUE_WEAR_ENGINE = "Wear Engine Service";
    private static final String TAG = "WearEngineBiOperate";
    private static ExecutorService mSingleThreadPool = Executors.newSingleThreadExecutor();
    private long mCallingEndTime;
    private long mCallingStartTime;

    public void init() {
        this.mCallingStartTime = System.currentTimeMillis();
    }

    public void biApiCalling(final Context context, String str, String str2, String str3) {
        tos.a(TAG, "biApiCalling enter!");
        if (context == null) {
            tos.e(TAG, "biApiCalling:context is null");
            return;
        }
        this.mCallingEndTime = System.currentTimeMillis();
        final ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(16);
        concurrentHashMap.put("service", SERVICE_VALUE_WEAR_ENGINE);
        concurrentHashMap.put("package", str);
        concurrentHashMap.put("version", "5.0.3.304");
        String e = tri.e(context, str);
        if (e == null) {
            e = "";
        }
        concurrentHashMap.put("appVersion", e);
        concurrentHashMap.put("appID", String.valueOf(tri.j(context, str)));
        concurrentHashMap.put("result", str3);
        concurrentHashMap.put("apiName", str2);
        concurrentHashMap.put("country", tri.a());
        concurrentHashMap.put("callTime", String.valueOf(System.currentTimeMillis()));
        concurrentHashMap.put("costTime", String.valueOf(this.mCallingEndTime - this.mCallingStartTime));
        final String value = OperationCode.WEAR_ENGINE_API_INVOKE_60000.value();
        mSingleThreadPool.execute(new Runnable() { // from class: com.huawei.wearengine.common.WearEngineBiOperate.2
            @Override // java.lang.Runnable
            public void run() {
                WearEngineBiOperate.this.reflectInvokeBiApiCalling(context, value, concurrentHashMap);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reflectInvokeBiApiCalling(Context context, String str, Map<String, String> map) {
        try {
            Class<?> cls = Class.forName(BI_ANALYTICS_UTIL);
            tos.b(TAG, "reflectInvokeBiApiCalling biAnalyticsSetEvent");
            Method declaredMethod = cls.getDeclaredMethod("biAnalyticsSetEvent", Context.class, String.class, Map.class, Integer.TYPE);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(cls, context, str, map, 0);
        } catch (ClassNotFoundException unused) {
            tos.e(TAG, "reflectInvokeBiApiCalling ClassNotFoundException");
        } catch (IllegalAccessException unused2) {
            tos.e(TAG, "reflectInvokeBiApiCalling IllegalAccessException");
        } catch (IllegalArgumentException unused3) {
            tos.e(TAG, "reflectInvokeBiApiCalling IllegalArgumentException");
        } catch (NoSuchMethodException unused4) {
            tos.e(TAG, "reflectInvokeBiApiCalling NoSuchMethodException");
        } catch (InvocationTargetException unused5) {
            tos.e(TAG, "reflectInvokeBiApiCalling InvocationTargetException");
        }
    }
}
