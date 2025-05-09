package com.huawei.hms.maps.internal;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import com.huawei.hms.common.Preconditions;
import com.huawei.hms.feature.dynamic.DynamicModule;
import com.huawei.hms.feature.dynamic.ObjectWrapper;
import com.huawei.hms.maps.MapClientIdentify;
import com.huawei.hms.maps.internal.ICreator;
import com.huawei.hms.maps.utils.DynamicUtil;
import com.huawei.hms.maps.utils.LogM;

/* loaded from: classes4.dex */
public class mab {

    /* renamed from: a, reason: collision with root package name */
    private static Context f4968a = null;
    private static ICreator b = null;
    private static boolean c = true;
    private static volatile boolean d = false;
    private static int e;
    private static SharedPreferences f;

    private static Context f(Context context) {
        String str;
        try {
            DynamicUtil.handlerDynamicLoadAfterLogic("huawei_module_huaweimaps", context);
            if (DynamicUtil.getDynamicModule() == null) {
                synchronized (mab.class) {
                    if (DynamicUtil.getDynamicModule() == null) {
                        DynamicUtil.setDynamicModule(DynamicModule.loadV3(context, DynamicModule.PREFER_HIGHEST_OR_REMOTE_VERSION, "huawei_module_huaweimaps"));
                    }
                }
            }
            if (DynamicUtil.getDynamicModule() != null) {
                return DynamicUtil.getDynamicModule().getModuleContext();
            }
            return null;
        } catch (DynamicModule.LoadingException e2) {
            LogM.e("MapCreator", "Loading mapRoute dynamically failed, exception is " + e2.getMessage());
            try {
                Bundle bundle = e2.getBundle();
                if (bundle != null && bundle.getInt("errcode") == 2) {
                    LogM.e("MapCreator", "getRemoteContext: LoadingException: errcode = " + bundle.getInt("errcode"));
                    Intent intent = (Intent) bundle.getParcelable("resolution");
                    if (intent == null) {
                        LogM.e("MapCreator", "null intent, please check it.");
                        return null;
                    }
                    LogM.e("MapCreator", "get intent successfully.");
                    intent.setFlags(268435456);
                    if (!c) {
                        return null;
                    }
                    try {
                        context.startActivity(intent);
                        c = false;
                        LogM.e("MapCreator", "startUpdateActivity Success.");
                        return null;
                    } catch (ActivityNotFoundException unused) {
                        LogM.e("MapCreator", "startActivity error ActivityNotFound.");
                        return null;
                    }
                }
                StringBuilder sb = new StringBuilder();
                sb.append("getRemoteContext: LoadingException: ");
                if (bundle == null) {
                    str = "bundle = null";
                } else {
                    str = "errcode = " + bundle.getInt("errcode");
                }
                sb.append(str);
                sb.append(", and retryCount is ");
                sb.append(e);
                LogM.e("MapCreator", sb.toString());
                if (!DynamicUtil.isAndroidN() && !d && Thread.currentThread() != Looper.getMainLooper().getThread()) {
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException unused2) {
                        LogM.e("MapCreator", "InterruptedException: getRemoteContext failed.");
                    }
                    int i = e;
                    if (i < 200) {
                        e = i + 1;
                        return f(context);
                    }
                    LogM.e("MapCreator", "Failed to obtain remote context. The maximum number of times has been reached.");
                }
                return null;
            } catch (Throwable unused3) {
                LogM.e("MapCreator", "get bundleInfo error: ");
                return null;
            }
        }
    }

    public static SharedPreferences e(Context context) {
        if (f == null) {
            f = context.getSharedPreferences("com.huawei.hmsmap.updateFlag", 0);
        }
        return f;
    }

    public static Context d(Context context) {
        MapClientIdentify.setAppContext(context);
        Context context2 = f4968a;
        if (context2 != null) {
            return context2;
        }
        Context f2 = f(context);
        f4968a = f2;
        return f2;
    }

    public static ICreator c(Context context) {
        synchronized (mab.class) {
            Preconditions.checkNotNull(context);
            ICreator iCreator = b;
            if (iCreator != null) {
                return iCreator;
            }
            LogM.i("MapCreator", "Making Creator dynamically");
            ICreator iCreator2 = null;
            try {
                Context d2 = d(context);
                if (d2 == null) {
                    LogM.e("MapCreator", "getRemoteMapContext failed");
                    return null;
                }
                Object newInstance = d2.getClassLoader().loadClass("com.huawei.hms.maps.CreatorImpl").newInstance();
                if (newInstance instanceof IBinder) {
                    ICreator asInterface = ICreator.Stub.asInterface((IBinder) newInstance);
                    asInterface.init(ObjectWrapper.wrap(d2.getResources()), 1000);
                    iCreator2 = asInterface;
                }
                b = iCreator2;
                return iCreator2;
            } catch (Throwable unused) {
                LogM.e("MapCreator", "loadClass failed");
                f4968a = null;
                DynamicUtil.setDynamicModule(null);
                return null;
            }
        }
    }

    public static void b(boolean z) {
        SharedPreferences sharedPreferences = f;
        if (sharedPreferences == null) {
            return;
        }
        sharedPreferences.edit().putBoolean("providerUpdateFlag", z).apply();
    }

    public static ICreator b(Context context) {
        d = true;
        ICreator c2 = c(context);
        d = false;
        return c2;
    }

    protected static void a(boolean z) {
        c = z;
    }

    public static void a() {
        f4968a = null;
        b = null;
    }

    public static ICreator a(Context context) {
        if (!DynamicUtil.isAndroidN()) {
            return b;
        }
        LogM.i("MapCreator", "get creator async.");
        return c(context);
    }
}
