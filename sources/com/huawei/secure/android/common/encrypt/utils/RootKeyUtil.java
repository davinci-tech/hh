package com.huawei.secure.android.common.encrypt.utils;

/* loaded from: classes6.dex */
public class RootKeyUtil {
    private static final String b = "RootKeyUtil";

    /* renamed from: a, reason: collision with root package name */
    private byte[] f8603a = null;

    private RootKeyUtil() {
    }

    private void a(String str, String str2, String str3, String str4) {
        a(str, str2, str3, HexUtil.hexStr2ByteArray(str4));
    }

    public static RootKeyUtil newInstance(String str, String str2, String str3, String str4) {
        RootKeyUtil rootKeyUtil = new RootKeyUtil();
        rootKeyUtil.a(str, str2, str3, str4);
        return rootKeyUtil;
    }

    public byte[] getRootKey() {
        return (byte[]) this.f8603a.clone();
    }

    public String getRootKeyHex() {
        return HexUtil.byteArray2HexStr(this.f8603a);
    }

    public static RootKeyUtil newInstance(String str, String str2, String str3, byte[] bArr) {
        RootKeyUtil rootKeyUtil = new RootKeyUtil();
        rootKeyUtil.a(str, str2, str3, bArr);
        return rootKeyUtil;
    }

    private void a(String str, String str2, String str3, byte[] bArr) {
        b.c(b, "initRootKey: sha256");
        this.f8603a = BaseKeyUtil.exportRootKey(str, str2, str3, bArr, true);
    }
}
