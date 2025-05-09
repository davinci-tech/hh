package com.huawei.updatesdk.b.h;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public final class a {
    private static final Map<Integer, String> d;
    private static a e;

    /* renamed from: a, reason: collision with root package name */
    private int f10845a;
    private int b = 0;
    private String c = "";

    public String c() {
        return this.c;
    }

    public int b() {
        return this.b;
    }

    public int a() {
        return this.f10845a;
    }

    private void h() {
        try {
            Class<?> cls = Class.forName("com.hihonor.android.os.Build");
            String str = (String) cls.getDeclaredField("MAGIC_VERSION").get(cls);
            if (str == null) {
                str = "";
            }
            this.c = str;
        } catch (Throwable th) {
            com.huawei.updatesdk.a.a.a.c("SystemSupportUtil", "initMagicVersion, error: " + th.getMessage());
        }
    }

    private void g() {
        try {
            Class<?> cls = Class.forName("com.hihonor.android.os.Build$VERSION");
            this.b = cls.getDeclaredField("MAGIC_SDK_INT").getInt(cls);
        } catch (Throwable th) {
            com.huawei.updatesdk.a.a.a.c("SystemSupportUtil", "initMagicApiLevel, error: " + th.getMessage());
        }
    }

    public static a f() {
        return e;
    }

    private int e() {
        return com.huawei.updatesdk.a.a.d.i.c.a("ro.build.hw_emui_api_level", 0);
    }

    private int d() {
        String a2 = a(com.huawei.updatesdk.a.a.d.i.c.a("ro.build.version.emui", ""));
        if (TextUtils.isEmpty(a2)) {
            return 0;
        }
        for (Map.Entry<Integer, String> entry : d.entrySet()) {
            if (a2.equals(entry.getValue())) {
                return entry.getKey().intValue();
            }
        }
        return 0;
    }

    private String a(String str) {
        if (!TextUtils.isEmpty(str)) {
            String[] split = str.split("_");
            if (split.length == 2) {
                return split[1];
            }
        }
        return "";
    }

    private a() {
        int e2 = e();
        this.f10845a = e2;
        if (e2 == 0) {
            this.f10845a = d();
        }
        g();
        h();
        com.huawei.updatesdk.a.a.a.b("SystemSupportUtil", "emuiVersion:" + this.f10845a + ", magicApiLevel:" + this.b + ", magicVersion:" + this.c);
    }

    static {
        HashMap hashMap = new HashMap();
        d = hashMap;
        e = new a();
        hashMap.put(1, "1.0");
        hashMap.put(2, "1.5");
        hashMap.put(3, "1.6");
        hashMap.put(4, "2.0");
        hashMap.put(5, "2.0");
        hashMap.put(6, "2.3");
        hashMap.put(7, "3.0");
        hashMap.put(8, "3.0.5");
        hashMap.put(8, "3.1");
        hashMap.put(9, "4.0");
        hashMap.put(10, "4.1");
        hashMap.put(11, "5.0");
        hashMap.put(12, "5.1");
    }
}
