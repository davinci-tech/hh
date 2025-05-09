package com.huawei.hms.feature.dynamic.f;

import android.util.Base64;
import com.huawei.hms.common.util.Logger;

/* loaded from: classes4.dex */
public abstract class a {

    /* renamed from: a, reason: collision with root package name */
    private static final String f4520a = "Base64";

    public static byte[] a(String str) {
        if (str == null) {
            return new byte[0];
        }
        try {
            byte[] decode = Base64.decode(str, 2);
            if (decode != null) {
                return decode;
            }
        } catch (IllegalArgumentException e) {
            Logger.e(f4520a, "Decoding with Base64 IllegalArgumentException:", e);
        }
        return new byte[0];
    }

    public static String a(byte[] bArr) {
        String encodeToString;
        if (bArr == null) {
            return "";
        }
        try {
            encodeToString = Base64.encodeToString(bArr, 2);
        } catch (AssertionError e) {
            Logger.e(f4520a, "An exception occurred while encoding with Base64,AssertionError:", e);
        }
        return encodeToString != null ? encodeToString : "";
    }
}
