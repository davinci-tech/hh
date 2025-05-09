package com.huawei.secure.android.common.activity.protect;

import android.content.Intent;
import android.os.IBinder;
import android.os.Message;
import java.lang.reflect.Field;

/* loaded from: classes9.dex */
public class a implements IActivityProtect {

    /* renamed from: a, reason: collision with root package name */
    private static final String f8565a = "a";

    private void a(IBinder iBinder) throws Exception {
        Object invoke = Class.forName("android.app.ActivityManagerNative").getDeclaredMethod("getDefault", new Class[0]).invoke(null, new Object[0]);
        invoke.getClass().getDeclaredMethod("finishActivity", IBinder.class, Integer.TYPE, Intent.class).invoke(invoke, iBinder, 0, null);
    }

    @Override // com.huawei.secure.android.common.activity.protect.IActivityProtect
    public void finishLaunchActivity(Message message) {
        com.huawei.secure.android.common.activity.a.b(f8565a, "finishLaunchActivity: ");
        try {
            Object obj = message.obj;
            Field declaredField = obj.getClass().getDeclaredField("token");
            declaredField.setAccessible(true);
            a((IBinder) declaredField.get(obj));
        } catch (Exception unused) {
            com.huawei.secure.android.common.activity.a.a(f8565a, "finishLaunchActivity exception ");
        }
    }

    @Override // com.huawei.secure.android.common.activity.protect.IActivityProtect
    public void finishPauseActivity(Message message) {
        com.huawei.secure.android.common.activity.a.b(f8565a, "finishPauseActivity: ");
        try {
            a((IBinder) message.obj);
        } catch (Exception unused) {
            com.huawei.secure.android.common.activity.a.a(f8565a, "finishPauseActivity exception ");
        }
    }

    @Override // com.huawei.secure.android.common.activity.protect.IActivityProtect
    public void finishResumeActivity(Message message) {
        com.huawei.secure.android.common.activity.a.b(f8565a, "finishResumeActivity: ");
        try {
            a((IBinder) message.obj);
        } catch (Exception unused) {
            com.huawei.secure.android.common.activity.a.a(f8565a, "finishResumeActivity exception ");
        }
    }

    @Override // com.huawei.secure.android.common.activity.protect.IActivityProtect
    public void finishStopActivity(Message message) {
        com.huawei.secure.android.common.activity.a.b(f8565a, "finishStopActivity: ");
        try {
            a((IBinder) message.obj);
        } catch (Exception unused) {
            com.huawei.secure.android.common.activity.a.a(f8565a, "finishStopActivity exception ");
        }
    }
}
