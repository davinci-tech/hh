package defpackage;

import com.huawei.hms.android.SystemUtils;

/* loaded from: classes3.dex */
public class bet {

    /* renamed from: a, reason: collision with root package name */
    private static final String f346a = beo.b(SystemUtils.PRODUCT_BRAND, "");
    private static final String b = beo.b("ro.product.manufacturer", "");

    public static boolean b() {
        return "honor".equalsIgnoreCase(f346a) && "honor".equalsIgnoreCase(b);
    }

    public static boolean d() {
        return b() && a();
    }

    public static boolean a() {
        return beo.b("ro.build.version.sdk", 0) >= 31;
    }
}
