package com.huawei.secure.android.common.ssl.util;

import android.content.Context;
import android.content.pm.PackageManager;

/* loaded from: classes9.dex */
public class e {

    /* renamed from: a, reason: collision with root package name */
    private static final String f8637a = "e";

    public static String a(String str) {
        Context contextUtil = ContextUtil.getInstance();
        if (contextUtil == null) {
            return "";
        }
        try {
            return contextUtil.getPackageManager().getPackageInfo(str, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            d.b(f8637a, "getVersion NameNotFoundException : " + e.getMessage());
            return "";
        } catch (Exception e2) {
            d.b(f8637a, "getVersion: " + e2.getMessage());
            return "";
        } catch (Throwable unused) {
            d.b(f8637a, "throwable");
            return "";
        }
    }

    public static int b(String str) {
        Context contextUtil = ContextUtil.getInstance();
        if (contextUtil == null) {
            return 0;
        }
        try {
            return contextUtil.getPackageManager().getPackageInfo(str, 0).versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            d.b(f8637a, "getVersion NameNotFoundException");
            return 0;
        } catch (Exception e) {
            d.b(f8637a, "getVersion: " + e.getMessage());
            return 0;
        }
    }
}
