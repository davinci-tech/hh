package com.huawei.hms.framework.common;

import android.util.Base64;
import com.huawei.secure.android.common.util.SafeBase64;

/* loaded from: classes4.dex */
public class SecurityBase64Utils {
    private static final String SAFE_BASE64_PATH = "com.huawei.secure.android.common.util.SafeBase64";
    private static volatile boolean isAegisBase64LibraryLoaded = false;

    public static String encodeToString(byte[] bArr, int i) {
        if (isAegisBase64LibraryLoaded || checkCompatible(SAFE_BASE64_PATH)) {
            return SafeBase64.encodeToString(bArr, i);
        }
        try {
            return Base64.encodeToString(bArr, i);
        } catch (Exception unused) {
            return null;
        }
    }

    public static byte[] decode(String str, int i) {
        if (isAegisBase64LibraryLoaded || checkCompatible(SAFE_BASE64_PATH)) {
            return SafeBase64.decode(str, i);
        }
        try {
            return Base64.decode(str, i);
        } catch (Exception unused) {
            return new byte[0];
        }
    }

    private static boolean checkCompatible(String str) {
        ClassLoader classLoader = SecurityBase64Utils.class.getClassLoader();
        if (classLoader == null) {
            return false;
        }
        try {
            classLoader.loadClass(str);
            synchronized (SecurityBase64Utils.class) {
                isAegisBase64LibraryLoaded = true;
            }
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }
}
