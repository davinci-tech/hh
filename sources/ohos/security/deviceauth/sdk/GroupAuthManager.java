package ohos.security.deviceauth.sdk;

import android.content.Context;

/* loaded from: classes7.dex */
public interface GroupAuthManager {
    int authDevice(long j, String str, DeviceAuthCallback deviceAuthCallback);

    void destroyService();

    int initService(Context context);

    int processData(long j, byte[] bArr, DeviceAuthCallback deviceAuthCallback);
}
