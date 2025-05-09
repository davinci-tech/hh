package com.huawei.health.h5pro.ext;

import android.text.TextUtils;
import com.huawei.health.h5pro.ext.hostapp.H5ProHostAppRuntimeApi;
import com.huawei.health.h5pro.ext.interceptor.H5ProDefaultInterceptor;
import com.huawei.health.h5pro.ext.interceptor.H5ProInterceptor;
import com.huawei.health.h5pro.ext.permission.H5ProPermissionExtApi;
import com.huawei.health.h5pro.ext.version.H5ProVersionUpgradeExtApi;
import com.huawei.health.h5pro.utils.LogUtil;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class H5ProResidentExtManager {

    /* renamed from: a, reason: collision with root package name */
    public static H5ProVersionUpgradeExtApi f2385a;
    public static H5ProHostAppRuntimeApi b;
    public static H5ProPermissionExtApi d;
    public static H5ProInterceptor e;

    public static void setVersionUpgradeExtApi(H5ProVersionUpgradeExtApi h5ProVersionUpgradeExtApi) {
        f2385a = h5ProVersionUpgradeExtApi;
    }

    public static void setVersionInterceptor(H5ProInterceptor h5ProInterceptor) {
        synchronized (H5ProResidentExtManager.class) {
            e = h5ProInterceptor;
        }
    }

    public static void setPermissionExtApi(H5ProPermissionExtApi h5ProPermissionExtApi) {
        d = h5ProPermissionExtApi;
    }

    public static void setHostAppApi(H5ProHostAppRuntimeApi h5ProHostAppRuntimeApi) {
        b = h5ProHostAppRuntimeApi;
    }

    public static void restoreExtApi(String[] strArr) {
        synchronized (H5ProResidentExtManager.class) {
            if (checkIsLost() && strArr != null && strArr.length > 0) {
                for (String str : strArr) {
                    registerByClass(str);
                }
            }
        }
    }

    public static void registerByClass(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            Constructor<?> defaultConstructor = getDefaultConstructor(Class.forName(str));
            if (defaultConstructor != null) {
                Object newInstance = defaultConstructor.newInstance(new Object[0]);
                if (newInstance instanceof H5ProPermissionExtApi) {
                    setPermissionExtApi((H5ProPermissionExtApi) newInstance);
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | InvocationTargetException e2) {
            LogUtil.i("H5PRO_H5ProResidentExtManager", "registerByClass: exception, " + e2.getMessage());
        }
    }

    public static H5ProVersionUpgradeExtApi getVersionUpgradeExtApi() {
        return f2385a;
    }

    public static H5ProInterceptor getVersionInterceptor() {
        H5ProInterceptor h5ProInterceptor;
        synchronized (H5ProResidentExtManager.class) {
            if (e == null) {
                e = new H5ProDefaultInterceptor();
            }
            h5ProInterceptor = e;
        }
        return h5ProInterceptor;
    }

    public static H5ProPermissionExtApi getPermissionExtApi() {
        return d;
    }

    public static H5ProHostAppRuntimeApi getHostAppRuntimeApi() {
        return b;
    }

    public static Constructor<?> getDefaultConstructor(Class<?> cls) {
        for (Constructor<?> constructor : cls.getConstructors()) {
            if (constructor.getParameterTypes() == null || constructor.getParameterTypes().length == 0) {
                return constructor;
            }
        }
        return null;
    }

    public static String[] getAllExtApiClass() {
        String[] strArr;
        synchronized (H5ProResidentExtManager.class) {
            ArrayList arrayList = new ArrayList();
            H5ProPermissionExtApi h5ProPermissionExtApi = d;
            if (h5ProPermissionExtApi != null) {
                arrayList.add(h5ProPermissionExtApi.getClass().getName());
            }
            strArr = (String[]) arrayList.toArray(new String[arrayList.size()]);
        }
        return strArr;
    }

    public static boolean checkIsLost() {
        return d == null;
    }
}
