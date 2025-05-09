package defpackage;

import android.content.Context;
import ohos.security.deviceauth.sdk.DeviceAuthCallback;
import ohos.security.deviceauth.sdk.GroupAuthManager;
import ohos.security.deviceauth.sdk.utils.DeviceUtil;

/* loaded from: classes7.dex */
public class uwj {

    /* renamed from: a, reason: collision with root package name */
    private static volatile uwj f17564a;
    private static final Object b = new Object();
    private static volatile GroupAuthManager c;

    private uwj() {
    }

    public static uwj e() {
        if (f17564a == null) {
            synchronized (b) {
                if (f17564a == null) {
                    f17564a = new uwj();
                }
            }
        }
        return f17564a;
    }

    public int b(Context context) {
        int c2 = uwp.c(context);
        if (c2 != 0) {
            uwn.e("DeviceAuthManager", "Init context failed.");
            return c2;
        }
        c(context);
        return c.initService(context);
    }

    public int e(long j, byte[] bArr, DeviceAuthCallback deviceAuthCallback) {
        if (c == null) {
            uwn.e("DeviceAuthManager", "processData:The instance of groupAuthManager is not initialized.");
            return -1;
        }
        return c.processData(j, bArr, deviceAuthCallback);
    }

    public int e(long j, String str, DeviceAuthCallback deviceAuthCallback) {
        if (c == null) {
            uwn.e("DeviceAuthManager", "authDevice:The instance of groupAuthManager is not initialized.");
            return -1;
        }
        return c.authDevice(j, str, deviceAuthCallback);
    }

    private static void c(Context context) {
        if (c == null) {
            synchronized (b) {
                if (c == null) {
                    if (DeviceUtil.isSystemServiceSupported(context)) {
                        c = new uwk();
                    } else {
                        c = new uwm();
                    }
                }
            }
        }
    }
}
