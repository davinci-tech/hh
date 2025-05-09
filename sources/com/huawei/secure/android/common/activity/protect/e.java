package com.huawei.secure.android.common.activity.protect;

import android.app.ActivityManager;
import android.app.servertransaction.ClientTransaction;
import android.content.Intent;
import android.os.IBinder;
import android.os.Message;
import java.lang.reflect.Method;

/* loaded from: classes9.dex */
public class e implements IActivityProtect {

    /* renamed from: a, reason: collision with root package name */
    private static final String f8569a = "e";

    private void a(Message message) throws Throwable {
        a(((ClientTransaction) message.obj).getActivityToken());
    }

    private void b(Message message) throws Throwable {
        Object obj = message.obj;
        a((IBinder) obj.getClass().getDeclaredMethod("getActivityToken", new Class[0]).invoke(obj, new Object[0]));
    }

    private void c(Message message) throws Throwable {
        Object obj = message.obj;
        a((IBinder) obj.getClass().getDeclaredField("mActivityToken").get(obj));
    }

    @Override // com.huawei.secure.android.common.activity.protect.IActivityProtect
    public void finishLaunchActivity(Message message) {
        com.huawei.secure.android.common.activity.a.b(f8569a, "finishLaunchActivity: ");
        try {
            a(message);
        } catch (Throwable unused) {
            com.huawei.secure.android.common.activity.a.a(f8569a, "finishLaunchActivity1 exception ");
            try {
                b(message);
            } catch (Throwable unused2) {
                com.huawei.secure.android.common.activity.a.a(f8569a, "finishLaunchActivity2 exception ");
                try {
                    c(message);
                } catch (Throwable unused3) {
                    com.huawei.secure.android.common.activity.a.a(f8569a, "finishLaunchActivity3 exception ");
                }
            }
        }
    }

    @Override // com.huawei.secure.android.common.activity.protect.IActivityProtect
    public void finishPauseActivity(Message message) {
    }

    @Override // com.huawei.secure.android.common.activity.protect.IActivityProtect
    public void finishResumeActivity(Message message) {
    }

    @Override // com.huawei.secure.android.common.activity.protect.IActivityProtect
    public void finishStopActivity(Message message) {
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
