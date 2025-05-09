package com.huawei.hms.kit.awareness.b;

import android.app.Activity;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Map;

/* loaded from: classes4.dex */
class HHB {
    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Object c(Field field) {
        field.setAccessible(true);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Object b(Field field) {
        field.setAccessible(true);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Object a(Field field) {
        field.setAccessible(true);
        return null;
    }

    static Activity a() {
        Map map;
        try {
            Class<?> cls = Class.forName("android.app.ActivityThread");
            Object invoke = cls.getMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]);
            final Field declaredField = cls.getDeclaredField("mActivities");
            AccessController.doPrivileged(new PrivilegedAction() { // from class: com.huawei.hms.kit.awareness.b.HHB$$ExternalSyntheticLambda0
                @Override // java.security.PrivilegedAction
                public final Object run() {
                    Object c;
                    c = HHB.c(declaredField);
                    return c;
                }
            });
            map = (Map) declaredField.get(invoke);
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchFieldException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
            com.huawei.hms.kit.awareness.b.a.c.d("getTopActivity", "AwarenessKit service upgrade failed. " + e.getMessage(), new Object[0]);
        }
        if (map == null) {
            return null;
        }
        for (Object obj : map.values()) {
            Class<?> cls2 = obj.getClass();
            final Field declaredField2 = cls2.getDeclaredField("paused");
            AccessController.doPrivileged(new PrivilegedAction() { // from class: com.huawei.hms.kit.awareness.b.HHB$$ExternalSyntheticLambda1
                @Override // java.security.PrivilegedAction
                public final Object run() {
                    Object b;
                    b = HHB.b(declaredField2);
                    return b;
                }
            });
            if (!declaredField2.getBoolean(obj)) {
                final Field declaredField3 = cls2.getDeclaredField("activity");
                AccessController.doPrivileged(new PrivilegedAction() { // from class: com.huawei.hms.kit.awareness.b.HHB$$ExternalSyntheticLambda2
                    @Override // java.security.PrivilegedAction
                    public final Object run() {
                        Object a2;
                        a2 = HHB.a(declaredField3);
                        return a2;
                    }
                });
                Object obj2 = declaredField3.get(obj);
                if (obj2 instanceof Activity) {
                    return (Activity) obj2;
                }
                return null;
            }
        }
        return null;
    }

    HHB() {
    }
}
