package com.huawei.hms.ads.uiengineloader;

/* loaded from: classes4.dex */
public final class f {

    /* renamed from: a, reason: collision with root package name */
    private static final String f4382a = "StringUtils";

    private static boolean b(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static int a(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            af.c(f4382a, "parseIntOrDefault exception: " + e.getClass().getSimpleName());
            return 0;
        }
    }
}
