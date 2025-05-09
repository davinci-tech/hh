package defpackage;

import com.huawei.wear.oversea.packageinfo.PackageUtilApi;

/* loaded from: classes7.dex */
public class stx {
    private static volatile PackageUtilApi c;
    private static final Object d = new Object();

    private static PackageUtilApi e() {
        if (c == null) {
            synchronized (d) {
                if (c == null) {
                    c = ste.a(ssz.e());
                }
            }
        }
        return c;
    }

    public static String b() {
        return e() == null ? "" : e().getWearWalletSdkVersionName();
    }
}
