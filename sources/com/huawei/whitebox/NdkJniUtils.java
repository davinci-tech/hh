package com.huawei.whitebox;

/* loaded from: classes2.dex */
public class NdkJniUtils {
    public native byte[] b(int i, byte[] bArr, byte[] bArr2);

    public native byte[] decrypt(byte[] bArr);

    public native byte[] encrypt(String str);

    public native void executeAuth(Object obj);

    public native String getStorageInfo(int i, int i2);

    public native boolean isAuthChecked();

    static {
        System.loadLibrary("whiteBoxJniLib");
    }
}
