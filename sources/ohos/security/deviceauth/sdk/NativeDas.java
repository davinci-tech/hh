package ohos.security.deviceauth.sdk;

import defpackage.uwn;

/* loaded from: classes7.dex */
public class NativeDas {
    private static final String TAG = "NativeDas";
    private static boolean sIsLibraryLoaded = false;

    public static native int addMemberToGroup(long j, long j2, String str, String str2);

    public static native int authDevice(long j, long j2, String str, DeviceAuthCallback deviceAuthCallback);

    public static native int confirmRequest(long j, long j2, String str, String str2);

    public static native int createGroup(long j, long j2, String str, String str2);

    public static native int deleteGroup(long j, long j2, String str, String str2);

    public static native int deleteMemberFromGroup(long j, long j2, String str, String str2);

    public static native void destroyService();

    public static native long getGaInstance();

    public static native long getGmInstance();

    public static native String getGroupInfo(long j, String str, String str2);

    public static native int initService(String str);

    public static native boolean isDeviceInGroup(long j, String str, String str2, String str3);

    public static native int processGaData(long j, long j2, byte[] bArr, DeviceAuthCallback deviceAuthCallback);

    public static native int processGmData(long j, long j2, byte[] bArr);

    public static native int registerGmCallback(long j, String str, DeviceAuthCallback deviceAuthCallback);

    public static native int unregisterGmCallback(long j, String str);

    static {
        try {
            System.loadLibrary("hichain_sdk_jni");
            sIsLibraryLoaded = true;
        } catch (SecurityException e) {
            uwn.e(TAG, "Jni library not allow be loaded! error: " + e.getMessage());
        } catch (UnsatisfiedLinkError e2) {
            uwn.e(TAG, "Jni library could not be loaded! error: " + e2.getMessage());
        }
    }

    private NativeDas() {
    }

    public static boolean isLibraryLoaded() {
        return sIsLibraryLoaded;
    }
}
