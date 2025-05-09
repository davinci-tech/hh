package ohos.security.deviceauth.sdk;

/* loaded from: classes7.dex */
public interface DeviceAuthCallback {
    void onError(long j, int i, int i2, String str);

    void onFinish(long j, int i, String str);

    String onRequest(long j, int i, String str);

    void onSessionKeyReturned(long j, byte[] bArr);

    boolean onTransmit(long j, byte[] bArr);
}
