package com.huawei.updatesdk.a.a.d;

import com.google.flatbuffers.reflection.BaseType;
import java.io.UnsupportedEncodingException;

/* loaded from: classes7.dex */
public class b {
    private int b = 1024;
    private int c = 0;

    /* renamed from: a, reason: collision with root package name */
    private byte[] f10803a = new byte[1024];

    public void a(byte[] bArr, int i) {
        if (i <= 0) {
            return;
        }
        byte[] bArr2 = this.f10803a;
        int length = bArr2.length;
        int i2 = this.c;
        if (length - i2 >= i) {
            System.arraycopy(bArr, 0, bArr2, i2, i);
        } else {
            byte[] bArr3 = new byte[(bArr2.length + i) << 1];
            System.arraycopy(bArr2, 0, bArr3, 0, i2);
            System.arraycopy(bArr, 0, bArr3, this.c, i);
            this.f10803a = bArr3;
        }
        this.c += i;
    }

    public String a() {
        int i = this.c;
        if (i <= 0) {
            return null;
        }
        try {
            return new String(this.f10803a, 0, i, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }

    public static String a(byte[] bArr) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] cArr2 = new char[bArr.length * 2];
        int i = 0;
        for (byte b : bArr) {
            int i2 = i + 1;
            cArr2[i] = cArr[(b >>> 4) & 15];
            i += 2;
            cArr2[i2] = cArr[b & BaseType.Obj];
        }
        return String.valueOf(cArr2);
    }
}
