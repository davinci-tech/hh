package com.huawei.secure.android.common.activity.protect;

import android.content.Intent;
import android.os.IBinder;
import android.os.Message;
import java.lang.reflect.Field;

/* loaded from: classes9.dex */
public class c implements IActivityProtect {

    /* renamed from: a, reason: collision with root package name */
    private static final String f8567a = "c";

    private void a(Message message) {
        try {
            Object obj = message.obj;
            Field declaredField = obj.getClass().getDeclaredField("arg1");
            declaredField.setAccessible(true);
            a((IBinder) declaredField.get(obj));
        } catch (Throwable unused) {
            com.huawei.secure.android.common.activity.a.a(f8567a, "finishSomeArgs exception  ");
        }
    }

    @Override // com.huawei.secure.android.common.activity.protect.IActivityProtect
    public void finishLaunchActivity(Message message) {
        com.huawei.secure.android.common.activity.a.b(f8567a, "finishLaunchActivity: ");
        try {
            Object obj = message.obj;
            Field declaredField = obj.getClass().getDeclaredField("token");
            declaredField.setAccessible(true);
            a((IBinder) declaredField.get(obj));
        } catch (Exception unused) {
            com.huawei.secure.android.common.activity.a.a(f8567a, "finishLaunchActivity exception ");
        }
    }

    @Override // com.huawei.secure.android.common.activity.protect.IActivityProtect
    public void finishPauseActivity(Message message) {
        com.huawei.secure.android.common.activity.a.b(f8567a, "finishPauseActivity: ");
        a(message);
    }

    @Override // com.huawei.secure.android.common.activity.protect.IActivityProtect
    public void finishResumeActivity(Message message) {
        com.huawei.secure.android.common.activity.a.b(f8567a, "finishResumeActivity: ");
        a(message);
    }

    @Override // com.huawei.secure.android.common.activity.protect.IActivityProtect
    public void finishStopActivity(Message message) {
        com.huawei.secure.android.common.activity.a.b(f8567a, "finishStopActivity: ");
        a(message);
    }

    private void a(IBinder iBinder) throws Exception {
        Object invoke = Class.forName("android.app.ActivityManagerNative").getDeclaredMethod("getDefault", new Class[0]).invoke(null, new Object[0]);
        Class<?> cls = invoke.getClass();
        Class cls2 = Integer.TYPE;
        cls.getDeclaredMethod("finishActivity", IBinder.class, cls2, Intent.class, cls2).invoke(invoke, iBinder, 0, null, 0);
    }
}
