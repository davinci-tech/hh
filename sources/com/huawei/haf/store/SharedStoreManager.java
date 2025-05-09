package com.huawei.haf.store;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.HafRuntimeException;
import com.huawei.haf.common.utils.AppInfoUtils;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.constant.WatchFaceConstant;
import health.compact.a.LogUtil;
import health.compact.a.ProcessUtil;
import health.compact.a.ReflectionUtils;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public final class SharedStoreManager {
    private static KeyValueStoreFactory d;
    private static final Map<String, SharedPreferences> b = new HashMap();
    private static final Map<String, ExtendSharedPreferences> e = new HashMap();

    private SharedStoreManager() {
    }

    public static void d(KeyValueStoreFactory keyValueStoreFactory) {
        KeyValueStoreFactory keyValueStoreFactory2;
        if (keyValueStoreFactory == null || keyValueStoreFactory == (keyValueStoreFactory2 = d)) {
            return;
        }
        if (keyValueStoreFactory2 != null && !b.isEmpty()) {
            LogUtil.a("HAF_SharedStore", "The setKeyValueStoreFactory needs to be initialized before business use");
        } else {
            d = keyValueStoreFactory;
            LogUtil.c("HAF_SharedStore", "setKeyValueStoreFactory factory=", keyValueStoreFactory);
        }
    }

    public static boolean e(String str) {
        File file = new File(AppInfoUtils.b("shared_prefs"), str + WatchFaceConstant.XML_SUFFIX);
        if (!file.exists()) {
            if (!new File(file.getPath() + ".bak").exists()) {
                return false;
            }
        }
        return true;
    }

    public static SharedPreferences zZ_(String str) {
        SharedPreferences sharedPreferences;
        Map<String, SharedPreferences> map = b;
        synchronized (map) {
            sharedPreferences = map.get(str);
            if (sharedPreferences == null) {
                sharedPreferences = zX_(str);
                map.put(str, sharedPreferences);
            }
        }
        return sharedPreferences;
    }

    private static SharedPreferences zX_(String str) {
        KeyValueStoreFactory keyValueStoreFactory = d;
        if (keyValueStoreFactory == null) {
            LogUtil.c("HAF_SharedStore", "createKeyValueStore default name=", str);
            return c(str);
        }
        SharedPreferences create = keyValueStoreFactory.create(str);
        if (create != null) {
            return create;
        }
        throw new HafRuntimeException("createKeyValueStore fail. name=" + str);
    }

    public static SharedPreferences Ab_(String str) {
        return c(str);
    }

    public static ExtendSharedPreferences c(String str) {
        ExtendSharedPreferences extendSharedPreferences;
        boolean z;
        Map<String, ExtendSharedPreferences> map = e;
        synchronized (map) {
            extendSharedPreferences = map.get(str);
            if (extendSharedPreferences == null) {
                extendSharedPreferences = d(str, map.size());
                map.put(str, extendSharedPreferences);
                z = false;
            } else {
                z = true;
            }
        }
        if (z && (extendSharedPreferences instanceof LocalSharedPreferences)) {
            ((LocalSharedPreferences) extendSharedPreferences).a(str);
        }
        return extendSharedPreferences;
    }

    private static ExtendSharedPreferences d(String str, int i) {
        boolean z;
        ExtendSharedPreferences remoteSharedPreferences;
        ProviderInfo Aa_ = Aa_(str);
        if (Aa_ != null) {
            LogUtil.c("HAF_SharedStore", "newSharedPreferences name=", Aa_.name, ", processName=", Aa_.processName);
            z = ProcessUtil.b().equals(Aa_.processName);
        } else {
            z = true;
        }
        if (z) {
            remoteSharedPreferences = new LocalSharedPreferences(str, Aa_ == null ? 4 : 0);
        } else {
            remoteSharedPreferences = new RemoteSharedPreferences(str);
        }
        Object[] objArr = new Object[6];
        objArr[0] = "newSharedPreferences is ";
        objArr[1] = z ? "local" : "remote";
        objArr[2] = ", name=";
        objArr[3] = str;
        objArr[4] = ", map size=";
        objArr[5] = Integer.valueOf(i + 1);
        LogUtil.c("HAF_SharedStore", objArr);
        return remoteSharedPreferences;
    }

    private static ProviderInfo Aa_(String str) {
        PackageInfo packageInfo;
        Class<?> b2;
        Context e2 = BaseApplication.e();
        try {
            packageInfo = e2.getPackageManager().getPackageInfo(e2.getPackageName(), 8);
        } catch (PackageManager.NameNotFoundException unused) {
        }
        if (packageInfo.providers == null) {
            return null;
        }
        String a2 = a(e2, str);
        for (ProviderInfo providerInfo : packageInfo.providers) {
            if (a2.equals(providerInfo.authority) && (b2 = ReflectionUtils.b(providerInfo.name)) != null && RemoteStoreContentProvider.class.isAssignableFrom(b2)) {
                return providerInfo;
            }
        }
        return null;
    }

    private static String a(Context context, String str) {
        return context.getPackageName() + ".haf_store_sp." + str;
    }

    static Uri zY_(String str, String str2) {
        String str3;
        StringBuilder sb = new StringBuilder("content://");
        sb.append(a(BaseApplication.e(), str));
        if (TextUtils.isEmpty(str2)) {
            str3 = "";
        } else {
            str3 = "/" + str2;
        }
        sb.append(str3);
        return Uri.parse(sb.toString());
    }
}
