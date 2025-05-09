package defpackage;

import android.content.Context;
import ohos.security.deviceauth.sdk.DeviceAuthCallback;
import ohos.security.deviceauth.sdk.GroupAuthManager;
import ohos.security.deviceauth.sdk.NativeDas;

/* loaded from: classes7.dex */
class uwm implements GroupAuthManager {

    /* renamed from: a, reason: collision with root package name */
    private long f17566a;
    private boolean e = false;

    uwm() {
    }

    @Override // ohos.security.deviceauth.sdk.GroupAuthManager
    public int initService(Context context) {
        if (this.e) {
            return 0;
        }
        if (!NativeDas.isLibraryLoaded()) {
            uwn.e("NativeGroupAuthManager", "The local library was not loaded successfully, the service could not be initialized.");
            return -1;
        }
        if (context == null || context.getFilesDir() == null) {
            uwn.e("NativeGroupAuthManager", "initService: Invalid context.");
            return -1;
        }
        int initService = NativeDas.initService(context.getFilesDir().getPath());
        if (initService != 0) {
            uwn.e("NativeGroupAuthManager", "initService: Init group manager service failed.");
            return initService;
        }
        long gaInstance = NativeDas.getGaInstance();
        this.f17566a = gaInstance;
        if (gaInstance == 0) {
            uwn.e("NativeGroupAuthManager", "initService: Get instance of ga failed.");
            return -1;
        }
        this.e = true;
        return 0;
    }

    @Override // ohos.security.deviceauth.sdk.GroupAuthManager
    public void destroyService() {
        if (this.e) {
            NativeDas.destroyService();
            this.e = false;
        }
    }

    @Override // ohos.security.deviceauth.sdk.GroupAuthManager
    public int processData(long j, byte[] bArr, DeviceAuthCallback deviceAuthCallback) {
        if (!this.e) {
            uwn.e("NativeGroupAuthManager", "The service is not started. Please call the initService first.");
            return -1;
        }
        return NativeDas.processGaData(this.f17566a, j, bArr, deviceAuthCallback);
    }

    @Override // ohos.security.deviceauth.sdk.GroupAuthManager
    public int authDevice(long j, String str, DeviceAuthCallback deviceAuthCallback) {
        if (!this.e) {
            uwn.e("NativeGroupAuthManager", "The service is not started. Please call the initService first.");
            return -1;
        }
        return NativeDas.authDevice(this.f17566a, j, str, deviceAuthCallback);
    }
}
