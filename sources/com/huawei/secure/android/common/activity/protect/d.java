package com.huawei.secure.android.common.activity.protect;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.IBinder;
import android.os.Message;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* loaded from: classes9.dex */
public class d implements IActivityProtect {

    /* renamed from: a, reason: collision with root package name */
    private static final String f8568a = "d";

    private void a(Message message) {
        try {
            Object obj = message.obj;
            Field declaredField = obj.getClass().getDeclaredField("arg1");
            declaredField.setAccessible(true);
            a((IBinder) declaredField.get(obj));
        } catch (Throwable unused) {
            com.huawei.secure.android.common.activity.a.a(f8568a, "finishLaunchActivity exception ");
        }
    }

    @Override // com.huawei.secure.android.common.activity.protect.IActivityProtect
    public void finishLaunchActivity(Message message) {
        com.huawei.secure.android.common.activity.a.b(f8568a, "finishLaunchActivity: ");
        try {
            Object obj = message.obj;
            Field declaredField = obj.getClass().getDeclaredField("token");
            declaredField.setAccessible(true);
            a((IBinder) declaredField.get(obj));
        } catch (Exception unused) {
            com.huawei.secure.android.common.activity.a.a(f8568a, "finishLaunchActivity exception ");
        }
    }

    @Override // com.huawei.secure.android.common.activity.protect.IActivityProtect
    public void finishPauseActivity(Message message) {
        com.huawei.secure.android.common.activity.a.b(f8568a, "finishPauseActivity: ");
        a(message);
    }

    @Override // com.huawei.secure.android.common.activity.protect.IActivityProtect
    public void finishResumeActivity(Message message) {
        com.huawei.secure.android.common.activity.a.b(f8568a, "finishResumeActivity: ");
        a(message);
    }

    @Override // com.huawei.secure.android.common.activity.protect.IActivityProtect
    public void finishStopActivity(Message message) {
        com.huawei.secure.android.common.activity.a.b(f8568a, "finishStopActivity: ");
        a(message);
    }

    private void a(IBinder iBinder) throws Exception {
        Object invoke = ActivityManager.class.getDeclaredMethod("getService", new Class[0]).invoke(null, new Object[0]);
        Class<?> cls = invoke.getClass();
        Class cls2 = Integer.TYPE;
        Method declaredMethod = cls.getDeclaredMethod("finishActivity", IBinder.class, cls2, Intent.class, cls2);
        declaredMethod.setAccessible(true);
        declaredMethod.invoke(invoke, iBinder, 0, null, 0);
    }
}
