package com.huawei.hmf.services.inject;

import android.app.PendingIntent;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class InjectInstanceFactoryRegistry {
    private static Map<Class<?>, Factory> mRegistry = new HashMap();

    public static abstract class Factory {
        public abstract InjectInstanceCreator create(Object obj);
    }

    public static void registerFactory(Class<?> cls, Factory factory) {
        mRegistry.put(cls, factory);
    }

    public static InjectInstanceCreator create(Object obj) {
        if (obj instanceof PendingIntent) {
            return new RemoteUIModuleCreator((PendingIntent) obj);
        }
        if (obj instanceof Class) {
            return new UIModuleCreator((Class) obj);
        }
        Factory factory = mRegistry.get(obj.getClass());
        if (factory != null) {
            return factory.create(obj);
        }
        return null;
    }
}
