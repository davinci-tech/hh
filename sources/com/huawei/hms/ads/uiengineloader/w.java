package com.huawei.hms.ads.uiengineloader;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import com.huawei.hms.ads.dynamic.IDynamicLoader;
import com.huawei.hms.ads.dynamic.IObjectWrapper;
import java.io.File;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes4.dex */
public final class w {

    /* renamed from: a, reason: collision with root package name */
    private static final String f4397a = "dl_LoadHelper";
    private static final String b = "DynamicLoader";

    public static void a(final int i, final String str, final String[] strArr, String str2) {
        com.huawei.hms.ads.dynamic.b.a(str2).execute(new com.huawei.hms.ads.dynamicloader.k(new Runnable() { // from class: com.huawei.hms.ads.uiengineloader.w.1
            @Override // java.lang.Runnable
            public final void run() {
                for (String str3 : strArr) {
                    if (Integer.parseInt(str3) < i) {
                        af.b(w.f4397a, "Delete low version:" + i + " in modulePath.");
                        ad.b(str + File.separator + str3);
                    }
                }
            }
        }));
    }

    public static IBinder a(Context context, String str) throws com.huawei.hms.ads.dynamicloader.j {
        if (str != null) {
            try {
                if (!TextUtils.isEmpty(str)) {
                    return (IBinder) new com.huawei.hms.ads.dynamicloader.d(str, ClassLoader.getSystemClassLoader()).loadClass("DynamicLoader").getConstructor(new Class[0]).newInstance(new Object[0]);
                }
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
                throw new com.huawei.hms.ads.dynamicloader.j("Failed to instantiate dynamic loader:" + e.getMessage());
            }
        }
        throw new com.huawei.hms.ads.dynamicloader.j("Failed to get dynamicLoader path.");
    }

    public static Context a(Context context, String str, Bundle bundle, IDynamicLoader iDynamicLoader) {
        try {
            IObjectWrapper load = iDynamicLoader.load(ah.a(context), str, bundle.getInt("module_version"), ah.a(bundle));
            if (ah.a(load) == null) {
                af.c(f4397a, "Get remote context is null.");
                return null;
            }
            if (ah.a(load) instanceof Context) {
                af.b(f4397a, "Get context for the module:" + str + " success.");
                return (Context) ah.a(load);
            }
            af.c(f4397a, "Incorrect context type.");
            return null;
        } catch (Exception e) {
            af.c(f4397a, "Failed to get remote module context." + e.getClass().getSimpleName());
            return null;
        }
    }
}
