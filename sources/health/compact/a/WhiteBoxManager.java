package health.compact.a;

import com.huawei.hwcommonmodel.application.BaseApplication;

/* loaded from: classes2.dex */
public class WhiteBoxManager {
    private static WhiteBoxManager b;
    private static final Object c = new Object();

    public static WhiteBoxManager d() {
        WhiteBoxManager whiteBoxManager;
        synchronized (c) {
            if (b == null) {
                b = new WhiteBoxManager();
            }
            whiteBoxManager = b;
        }
        return whiteBoxManager;
    }

    public byte[] b(String str) {
        return HealthWhiteBoxManager.d(BaseApplication.getContext()).d(str);
    }

    public byte[] a(byte[] bArr) {
        return HealthWhiteBoxManager.d(BaseApplication.getContext()).e(bArr);
    }

    public String d(int i, int i2) {
        return HealthWhiteBoxManager.d(BaseApplication.getContext()).b(i, i2);
    }

    public byte[] c(int i, byte[] bArr, byte[] bArr2) {
        return HealthWhiteBoxManager.d(BaseApplication.getContext()).d(i, bArr, bArr2);
    }
}
