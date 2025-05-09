package com.huawei.hms.network.embedded;

import com.huawei.operation.jsoperation.JsUtil;
import java.io.IOException;

/* loaded from: classes9.dex */
public final class u9 {
    public static final int b = 16384;
    public static final byte c = 0;
    public static final byte d = 1;
    public static final byte e = 2;
    public static final byte f = 3;
    public static final byte g = 4;
    public static final byte h = 5;
    public static final byte i = 6;
    public static final byte j = 7;
    public static final byte k = 8;
    public static final byte l = 9;
    public static final byte m = 0;
    public static final byte n = 1;
    public static final byte o = 1;
    public static final byte p = 4;
    public static final byte q = 4;
    public static final byte r = 8;
    public static final byte s = 32;
    public static final byte t = 32;

    /* renamed from: a, reason: collision with root package name */
    public static final eb f5523a = eb.d("PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n");
    public static final String[] u = {JsUtil.ServiceType.DATA, "HEADERS", "PRIORITY", "RST_STREAM", "SETTINGS", "PUSH_PROMISE", "PING", "GOAWAY", "WINDOW_UPDATE", "CONTINUATION"};
    public static final String[] v = new String[64];
    public static final String[] w = new String[256];

    public static IOException b(String str, Object... objArr) throws IOException {
        throw new IOException(f8.a(str, objArr));
    }

    public static String a(boolean z, int i2, int i3, byte b2, byte b3) {
        String[] strArr = u;
        String a2 = b2 < strArr.length ? strArr[b2] : f8.a("0x%02x", Byte.valueOf(b2));
        String a3 = a(b2, b3);
        Object[] objArr = new Object[5];
        objArr[0] = z ? "<<" : ">>";
        objArr[1] = Integer.valueOf(i2);
        objArr[2] = Integer.valueOf(i3);
        objArr[3] = a2;
        objArr[4] = a3;
        return f8.a("%s 0x%08x %5d %-13s %s", objArr);
    }

    public static String a(byte b2, byte b3) {
        if (b3 == 0) {
            return "";
        }
        if (b2 != 2 && b2 != 3) {
            if (b2 == 4 || b2 == 6) {
                return b3 == 1 ? "ACK" : w[b3];
            }
            if (b2 != 7 && b2 != 8) {
                String[] strArr = v;
                String str = b3 < strArr.length ? strArr[b3] : w[b3];
                return (b2 != 5 || (b3 & 4) == 0) ? (b2 != 0 || (b3 & 32) == 0) ? str : str.replace("PRIORITY", "COMPRESSED") : str.replace("HEADERS", "PUSH_PROMISE");
            }
        }
        return w[b3];
    }

    public static IllegalArgumentException a(String str, Object... objArr) {
        throw new IllegalArgumentException(f8.a(str, objArr));
    }

    static {
        int i2 = 0;
        int i3 = 0;
        while (true) {
            String[] strArr = w;
            if (i3 >= strArr.length) {
                break;
            }
            strArr[i3] = f8.a("%8s", Integer.toBinaryString(i3)).replace(' ', '0');
            i3++;
        }
        String[] strArr2 = v;
        strArr2[0] = "";
        strArr2[1] = "END_STREAM";
        int[] iArr = {1};
        strArr2[8] = "PADDED";
        strArr2[9] = strArr2[1] + "|PADDED";
        strArr2[4] = "END_HEADERS";
        strArr2[32] = "PRIORITY";
        strArr2[36] = "END_HEADERS|PRIORITY";
        int[] iArr2 = {4, 32, 36};
        for (int i4 = 0; i4 < 3; i4++) {
            int i5 = iArr2[i4];
            int i6 = iArr[0];
            String[] strArr3 = v;
            int i7 = i6 | i5;
            strArr3[i7] = strArr3[i6] + '|' + strArr3[i5];
            strArr3[i7 | 8] = strArr3[i6] + '|' + strArr3[i5] + "|PADDED";
        }
        while (true) {
            String[] strArr4 = v;
            if (i2 >= strArr4.length) {
                return;
            }
            if (strArr4[i2] == null) {
                strArr4[i2] = w[i2];
            }
            i2++;
        }
    }
}
