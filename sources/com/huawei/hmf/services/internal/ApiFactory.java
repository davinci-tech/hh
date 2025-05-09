package com.huawei.hmf.services.internal;

import android.util.Log;
import com.huawei.hmf.services.ApiSpec;
import java.lang.reflect.Constructor;

/* loaded from: classes4.dex */
public class ApiFactory {
    private static final String TAG = "ApiFactory";
    private static ObjectCache objectCache = new ObjectCache();

    public static <T> T create(ApiSpec apiSpec) {
        return (T) create(objectCache, apiSpec);
    }

    public static <T> T create(ApiSpec apiSpec, Object... objArr) {
        return (T) create(objectCache, apiSpec, objArr);
    }

    public static <T> T create(ObjectCache objectCache2, ApiSpec apiSpec) {
        T t;
        if (apiSpec == null) {
            return null;
        }
        try {
            if (apiSpec.isSingleton()) {
                synchronized (ApiFactory.class) {
                    t = (T) objectCache2.get(apiSpec.getType());
                    if (t == null) {
                        t = (T) apiSpec.getType().newInstance();
                        objectCache2.put(apiSpec.getType(), t);
                    }
                }
                return t;
            }
            return (T) apiSpec.getType().newInstance();
        } catch (Exception e) {
            Log.e(TAG, "create failed:" + e.getMessage());
            return null;
        }
    }

    public static <T> T create(ObjectCache objectCache2, ApiSpec apiSpec, Object... objArr) {
        T t;
        if (objArr.length == 0) {
            return (T) create(objectCache2, apiSpec);
        }
        if (apiSpec == null) {
            return null;
        }
        try {
            if (apiSpec.isSingleton()) {
                synchronized (ApiFactory.class) {
                    t = (T) objectCache2.get(apiSpec.getType());
                    if (t == null) {
                        t = (T) matchConstructor(apiSpec.getType(), objArr).newInstance(objArr);
                        objectCache2.put(apiSpec.getType(), t);
                    }
                }
                return t;
            }
            return (T) matchConstructor(apiSpec.getType(), objArr).newInstance(objArr);
        } catch (Exception e) {
            Log.e(TAG, "create failed with arguments:" + e.getMessage());
            return null;
        }
    }

    private static Constructor matchConstructor(Class cls, Object... objArr) {
        boolean z = false;
        for (Constructor<?> constructor : cls.getDeclaredConstructors()) {
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            if (parameterTypes.length == objArr.length) {
                for (int i = 0; i < objArr.length; i++) {
                    z = parameterTypes[i].isAssignableFrom(objArr[i].getClass());
                }
                if (z) {
                    return constructor;
                }
            }
        }
        throw new IllegalStateException("Do not match constructor with argument number: " + objArr.length);
    }
}
