package defpackage;

import com.huawei.haf.application.BaseApplication;
import health.compact.a.HealthWhiteBoxManager;

/* loaded from: classes3.dex */
public class bmo {
    public static byte[] a(byte[] bArr) {
        return HealthWhiteBoxManager.d(BaseApplication.e()).e(bArr);
    }

    public static String e(int i, int i2) {
        return HealthWhiteBoxManager.d(BaseApplication.e()).b(i, i2);
    }
}
