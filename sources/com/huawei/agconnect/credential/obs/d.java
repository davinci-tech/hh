package com.huawei.agconnect.credential.obs;

import android.content.Context;
import com.huawei.agconnect.common.api.Logger;

/* loaded from: classes2.dex */
public class d {

    /* renamed from: a, reason: collision with root package name */
    private static final String f1766a = "HMSInstanceIdWrapper";
    private Object b;

    public void d() {
        try {
            Logger.d(f1766a, "call hms wrapper : deleteAAID");
            this.b.getClass().getDeclaredMethod("deleteAAID", new Class[0]).invoke(this.b, new Object[0]);
        } catch (Throwable unused) {
            Logger.e(f1766a, "delete aaid fail");
        }
    }

    public long c() {
        try {
            Logger.d(f1766a, "call hms wrapper : getCreationTime");
            return ((Long) this.b.getClass().getDeclaredMethod("getCreationTime", new Class[0]).invoke(this.b, new Object[0])).longValue();
        } catch (Throwable unused) {
            Logger.e(f1766a, "get create time fail");
            return 0L;
        }
    }

    public String b() {
        try {
            Logger.d(f1766a, "call hms wrapper : getId");
            return (String) this.b.getClass().getDeclaredMethod("getId", new Class[0]).invoke(this.b, new Object[0]);
        } catch (Throwable unused) {
            Logger.e(f1766a, "get id fail");
            return null;
        }
    }

    public boolean a() {
        return this.b != null;
    }

    public d(Context context) {
        try {
            this.b = Class.forName("com.huawei.hms.aaid.HmsInstanceId").getDeclaredMethod("getInstance", Context.class).invoke(null, context);
        } catch (Throwable unused) {
            Logger.w(f1766a, "not find package HmsInstanceId");
        }
    }
}
