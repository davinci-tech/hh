package com.huawei.agconnect.datastore.core;

import android.util.Base64;
import android.util.Log;

/* loaded from: classes2.dex */
public class SafeBase64 {
    private static final String TAG = "SafeBase64";

    public static String encodeToString(byte[] bArr, int i, int i2, int i3) {
        try {
            return Base64.encodeToString(bArr, i, i2, i3);
        } catch (Exception e) {
            Log.e(TAG, "encodeToString error " + e.getMessage());
            return "";
        }
    }

    public static String encodeToString(byte[] bArr, int i) {
        try {
            return Base64.encodeToString(bArr, i);
        } catch (Exception e) {
            Log.e(TAG, "encodeToString error " + e.getMessage());
            return "";
        }
    }

    public static byte[] encode(byte[] bArr, int i, int i2, int i3) {
        try {
            return Base64.encode(bArr, i, i2, i3);
        } catch (Exception e) {
            Log.e(TAG, "encode error " + e.getMessage());
            return new byte[0];
        }
    }

    public static byte[] encode(byte[] bArr, int i) {
        try {
            return Base64.encode(bArr, i);
        } catch (Exception e) {
            Log.e(TAG, "encode error " + e.getMessage());
            return new byte[0];
        }
    }

    public static byte[] decode(byte[] bArr, int i, int i2, int i3) {
        try {
            return Base64.decode(bArr, i, i2, i3);
        } catch (Exception e) {
            Log.e(TAG, "decode error " + e.getMessage());
            return new byte[0];
        }
    }

    public static byte[] decode(byte[] bArr, int i) {
        try {
            return Base64.decode(bArr, i);
        } catch (Exception e) {
            Log.e(TAG, "decode error " + e.getMessage());
            return new byte[0];
        }
    }

    public static byte[] decode(String str, int i) {
        try {
            return Base64.decode(str, i);
        } catch (Exception e) {
            Log.e(TAG, "decode error " + e.getMessage());
            return new byte[0];
        }
    }
}
