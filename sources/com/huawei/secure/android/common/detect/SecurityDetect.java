package com.huawei.secure.android.common.detect;

import com.huawei.secure.android.common.detect.b.c;

/* loaded from: classes6.dex */
public class SecurityDetect {

    /* renamed from: a, reason: collision with root package name */
    private static final String f8576a = "SecurityDetect";

    public static boolean idj() {
        try {
            return SD.idj();
        } catch (Throwable th) {
            c.b(f8576a, "idj: " + th.getMessage());
            return false;
        }
    }

    public static boolean iej() {
        try {
            return SD.iej();
        } catch (Throwable th) {
            c.b(f8576a, "iej: " + th.getMessage());
            return false;
        }
    }

    public static boolean irpj() {
        try {
            return SD.irpj();
        } catch (Throwable th) {
            c.b(f8576a, "irpj: " + th.getMessage());
            return false;
        }
    }
}
