package defpackage;

import android.text.TextUtils;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
public class ctt {

    /* renamed from: a, reason: collision with root package name */
    private static byte[] f11471a;
    private static byte[] b;
    private static byte[] c;
    private static byte[] d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private String l;
    private String m;
    private String n;
    private String o;

    static {
        byte[] bArr = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
        f11471a = bArr;
        byte[] bArr2 = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90};
        b = bArr2;
        byte[] bArr3 = {97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122};
        d = bArr3;
        c = b(bArr2, bArr3, bArr);
    }

    public ctt(String str) {
        int c2 = !TextUtils.isEmpty(str) ? c(str.charAt(0)) : -1;
        int i = c2 & 3;
        this.f = i;
        this.g = (c2 >>> 2) & 3;
        this.i = (c2 >>> 4) & 1;
        this.j = (c2 >>> 5) & 1;
        this.l = a(i);
        this.m = a(this.g);
        this.n = String.valueOf(this.i);
        this.o = String.valueOf(this.j);
        d(this.l, this.m);
    }

    private void d(String str, String str2) {
        if ("00".equals(str)) {
            this.e = 0;
            return;
        }
        if (HiAnalyticsConstant.KeyAndValue.NUMBER_01.equals(str)) {
            this.e = 1;
            if ("00".equals(str2)) {
                this.h = 100;
                return;
            }
            if (HiAnalyticsConstant.KeyAndValue.NUMBER_01.equals(str2)) {
                this.h = 101;
                return;
            } else if ("10".equals(str2)) {
                this.h = 110;
                return;
            } else {
                this.h = 2000;
                return;
            }
        }
        if ("10".equals(str)) {
            this.e = 10;
            if ("00".equals(str2)) {
                this.h = 1000;
                return;
            } else if (HiAnalyticsConstant.KeyAndValue.NUMBER_01.equals(str2)) {
                this.h = 1001;
                return;
            } else {
                this.h = 2000;
                return;
            }
        }
        if ("11".equals(str)) {
            this.e = 11;
            if ("00".equals(str2)) {
                this.h = 1100;
                return;
            } else {
                this.h = 2000;
                return;
            }
        }
        LogUtil.h("EncryptProtal", "EncryptProtal confirmEncryptInformation invalid");
    }

    private String a(int i) {
        String binaryString = Integer.toBinaryString(i);
        if (binaryString.length() != 1) {
            return binaryString;
        }
        return "0" + binaryString;
    }

    private int c(char c2) {
        int i = 0;
        while (true) {
            byte[] bArr = c;
            if (i >= bArr.length) {
                return -1;
            }
            if (c2 == bArr[i]) {
                return i;
            }
            i++;
        }
    }

    private static byte[] b(byte[]... bArr) {
        int i = 0;
        for (byte[] bArr2 : bArr) {
            i += bArr2.length;
        }
        byte[] bArr3 = new byte[i];
        int i2 = 0;
        for (byte[] bArr4 : bArr) {
            System.arraycopy(bArr4, 0, bArr3, i2, bArr4.length);
            i2 += bArr4.length;
        }
        return bArr3;
    }

    public int a() {
        return this.h;
    }

    public String toString() {
        return "EncryptProtal [mEncryptAlgorithm =" + this.e + ", mEncryptMethod =" + this.h + System.lineSeparator() + ", mIntBit01 =" + this.f + ", mIntBit23 =" + this.g + ", mIntBit4 =" + this.i + ", mIntBit5 =" + this.j + System.lineSeparator() + ", mStrBit01 =" + this.l + ", mStrBit23 =" + this.m + ", mStrBit4 =" + this.n + ", mStrBit5 =" + this.o + "]";
    }
}
