package com.tencent.open.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.tencent.open.log.SLog;

/* loaded from: classes7.dex */
public class f {

    /* renamed from: a, reason: collision with root package name */
    private String f11373a;
    private String b;

    private f() {
        this.f11373a = "";
        this.b = "";
    }

    static class a {

        /* renamed from: a, reason: collision with root package name */
        private static f f11374a = new f();
    }

    public static f a() {
        return a.f11374a;
    }

    public void a(Context context) {
        if (context == null) {
            return;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("device_info_file", 4);
        String str = this.b;
        if (str == null || str.trim().isEmpty()) {
            this.b = sharedPreferences.getString("build_model", "");
            SLog.i("openSDK_LOG.DeviceInfoUtils", "init, model = " + this.b);
        }
        String str2 = this.f11373a;
        if (str2 == null || str2.trim().isEmpty()) {
            this.f11373a = sharedPreferences.getString("build_device", "");
            SLog.i("openSDK_LOG.DeviceInfoUtils", "init, device = " + this.f11373a);
        }
    }

    public String b(Context context) {
        return this.f11373a;
    }

    public String c(Context context) {
        return this.b;
    }

    public String b() {
        return this.b;
    }

    public void a(Context context, String str) {
        SLog.i("openSDK_LOG.DeviceInfoUtils", "setBuildModel, model = " + str);
        if (str == null || str.trim().isEmpty()) {
            this.b = "";
            if (context != null) {
                context.getSharedPreferences("device_info_file", 4).edit().remove("build_model").commit();
                return;
            }
            return;
        }
        String str2 = this.b;
        if (str2 != null && str2.equals(str)) {
            SLog.i("openSDK_LOG.DeviceInfoUtils", "setBuildModel, needn't update sp.");
            return;
        }
        this.b = str;
        if (context != null) {
            context.getSharedPreferences("device_info_file", 4).edit().putString("build_model", this.b).commit();
            SLog.i("openSDK_LOG.DeviceInfoUtils", "setBuildModel, update sp.");
        }
    }
}
