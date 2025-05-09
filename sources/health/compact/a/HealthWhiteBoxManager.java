package health.compact.a;

import android.content.Context;
import com.huawei.whitebox.NdkJniUtils;

/* loaded from: classes2.dex */
public class HealthWhiteBoxManager {
    private static HealthWhiteBoxManager b;
    private static final Object d = new Object();

    /* renamed from: a, reason: collision with root package name */
    private NdkJniUtils f13121a = new NdkJniUtils();
    private Context c;

    private HealthWhiteBoxManager(Context context) {
        this.c = context;
    }

    public static HealthWhiteBoxManager d(Context context) {
        HealthWhiteBoxManager healthWhiteBoxManager;
        synchronized (d) {
            if (b == null) {
                b = new HealthWhiteBoxManager(context);
            }
            healthWhiteBoxManager = b;
        }
        return healthWhiteBoxManager;
    }

    public byte[] d(String str) {
        if (!this.f13121a.isAuthChecked()) {
            this.f13121a.executeAuth(this.c);
        }
        return this.f13121a.encrypt(str);
    }

    public byte[] e(byte[] bArr) {
        if (!this.f13121a.isAuthChecked()) {
            this.f13121a.executeAuth(this.c);
        }
        return this.f13121a.decrypt(bArr);
    }

    public String b(int i, int i2) {
        if (!this.f13121a.isAuthChecked()) {
            this.f13121a.executeAuth(this.c);
        }
        return this.f13121a.getStorageInfo(i, i2);
    }

    public byte[] d(int i, byte[] bArr, byte[] bArr2) {
        if (!this.f13121a.isAuthChecked()) {
            this.f13121a.executeAuth(this.c);
        }
        return this.f13121a.b(i, bArr, bArr2);
    }
}
