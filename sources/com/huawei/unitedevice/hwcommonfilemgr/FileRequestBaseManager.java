package com.huawei.unitedevice.hwcommonfilemgr;

import android.content.Context;
import android.text.TextUtils;
import com.google.flatbuffers.reflection.BaseType;
import defpackage.blq;
import defpackage.blt;
import defpackage.sol;
import health.compact.a.LogUtil;

/* loaded from: classes9.dex */
public class FileRequestBaseManager {
    public FileRequestBaseManager(Context context) {
    }

    protected byte[] a(String str, int i, int i2, int i3, int i4) {
        StringBuilder sb = new StringBuilder(16);
        if (!TextUtils.isEmpty(str)) {
            String b = blq.b(str);
            sb.append(blq.b(1) + blq.b(b.length() / 2) + b);
        } else {
            LogUtil.c("Unite_FileRequestBaseManager", "5.44.1 file name is empty.");
        }
        sb.append(blq.b(2) + blq.b(1) + blq.b(i));
        if (i2 != -1 && i3 != -1) {
            sb.append(blq.b(5));
            sb.append(blq.b(4));
            sb.append(blq.d(i2));
            sb.append(blq.b(6));
            sb.append(blq.b(4));
            sb.append(blq.d(i3));
        } else {
            LogUtil.a("Unite_FileRequestBaseManager", "5.44.1 startTime or endTime is -1, startTime: ", Integer.valueOf(i2), ", end time: ", Integer.valueOf(i3));
        }
        if (i4 > 0 && i == 22) {
            LogUtil.c("Unite_FileRequestBaseManager", "5.44.1 dictTypeId : ", Integer.valueOf(i4));
            String d = blq.d(i4);
            sb.append(blq.b(12) + blq.a(d.length() / 2) + d);
        }
        return blq.a(sb.toString());
    }

    protected byte[] d(sol solVar) {
        StringBuilder sb = new StringBuilder(16);
        if (!TextUtils.isEmpty(solVar.m())) {
            String b = blq.b(solVar.m());
            sb.append(blq.b(1) + blq.b(b.length() / 2) + b);
        } else {
            LogUtil.c("Unite_FileRequestBaseManager", "5.44.1 file name is empty.");
        }
        sb.append(blq.b(2) + blq.b(1) + blq.b(solVar.u()));
        if (solVar.ak() != -1 && solVar.k() != -1) {
            sb.append(blq.b(5));
            sb.append(blq.b(4));
            sb.append(blq.d(solVar.ak()));
            sb.append(blq.b(6));
            sb.append(blq.b(4));
            sb.append(blq.d(solVar.k()));
        } else {
            LogUtil.a("Unite_FileRequestBaseManager", "5.44.1 startTime or endTime is -1, startTime: ", Integer.valueOf(solVar.ak()), ", end time: ", Integer.valueOf(solVar.k()));
        }
        if (!TextUtils.isEmpty(solVar.ae()) && !TextUtils.isEmpty(solVar.g()) && !TextUtils.isEmpty(solVar.ah()) && !TextUtils.isEmpty(solVar.h())) {
            String b2 = blq.b(solVar.j());
            String str = blq.b(11) + blq.b(b2.length() / 2) + b2;
            String b3 = blq.b(solVar.ae());
            String str2 = blq.b(7) + blq.b(b3.length() / 2) + b3;
            String b4 = blq.b(solVar.g());
            String str3 = blq.b(8) + blq.b(b4.length() / 2) + b4;
            String b5 = blq.b(solVar.ah());
            String str4 = blq.b(10) + blq.b(b5.length() / 2) + b5;
            String b6 = blq.b(solVar.h());
            String str5 = blq.b(11) + blq.b(b6.length() / 2) + b6;
            sb.append(str);
            sb.append(str2);
            sb.append(str3);
            sb.append(str4);
            sb.append(str5);
        } else {
            LogUtil.c("Unite_FileRequestBaseManager", "5.44.1 lack source or destination package info.");
        }
        return blq.a(sb.toString());
    }

    protected byte[] a(int i, int i2) {
        byte[] f = f(i);
        byte[] i3 = i(i2);
        byte[] bArr = new byte[f.length + i3.length];
        System.arraycopy(f, 0, bArr, 0, f.length);
        System.arraycopy(i3, 0, bArr, f.length, i3.length);
        return bArr;
    }

    private byte[] f(int i) {
        byte[] g = blq.g(i);
        byte[] bArr = new byte[g.length + 2];
        bArr[0] = 1;
        bArr[1] = 1;
        System.arraycopy(g, 0, bArr, 2, g.length);
        return bArr;
    }

    private byte[] i(int i) {
        byte[] g = blq.g(i);
        byte[] bArr = new byte[g.length + 2];
        bArr[0] = 2;
        bArr[1] = 1;
        System.arraycopy(g, 0, bArr, 2, g.length);
        return bArr;
    }

    protected byte[] b(int i) {
        byte[] n = n(i);
        byte[] d = d(2, (byte) 0);
        byte[] d2 = d(3, (byte) 0);
        byte[] d3 = d(3, (byte) 0);
        byte[] d4 = d(5, (byte) 0);
        byte[] bArr = new byte[n.length + d.length + d2.length + d3.length + d4.length];
        System.arraycopy(n, 0, bArr, 0, n.length);
        System.arraycopy(d, 0, bArr, n.length, d.length);
        System.arraycopy(d2, 0, bArr, n.length + d.length, d2.length);
        System.arraycopy(d3, 0, bArr, n.length + d.length + d2.length, d3.length);
        System.arraycopy(d4, 0, bArr, n.length + d.length + d2.length + d3.length, d4.length);
        blt.d("Unite_FileRequestBaseManager", bArr, "sendRequestParameter, deviceCommand:");
        return bArr;
    }

    private static byte[] n(int i) {
        byte[] g = blq.g(i);
        byte[] bArr = new byte[g.length + 2];
        bArr[0] = 1;
        bArr[1] = 1;
        System.arraycopy(g, 0, bArr, 2, g.length);
        return bArr;
    }

    protected byte[] a(int i, int i2, int i3, int i4, int i5) {
        byte[] h = h(i);
        byte[] j = j(i2);
        byte[] g = g(i3);
        LogUtil.c("Unite_FileRequestBaseManager", "dictTypeId ：", Integer.valueOf(i5), "fileType: ", Integer.valueOf(i4));
        byte[] k = (i5 <= 0 || i4 != 22) ? null : k(i5);
        if (k != null) {
            byte[] bArr = new byte[h.length + j.length + g.length + k.length];
            System.arraycopy(h, 0, bArr, 0, h.length);
            System.arraycopy(j, 0, bArr, h.length, j.length);
            System.arraycopy(g, 0, bArr, h.length + j.length, g.length);
            System.arraycopy(k, 0, bArr, h.length + j.length + g.length, k.length);
            return bArr;
        }
        byte[] bArr2 = new byte[h.length + j.length + g.length];
        System.arraycopy(h, 0, bArr2, 0, h.length);
        System.arraycopy(j, 0, bArr2, h.length, j.length);
        System.arraycopy(g, 0, bArr2, h.length + j.length, g.length);
        return bArr2;
    }

    private static byte[] h(int i) {
        byte[] g = blq.g(i);
        byte[] bArr = new byte[g.length + 2];
        bArr[0] = 1;
        bArr[1] = 1;
        System.arraycopy(g, 0, bArr, 2, g.length);
        return bArr;
    }

    private static byte[] j(int i) {
        byte[] i2 = blq.i(i);
        byte[] bArr = new byte[i2.length + 2];
        bArr[0] = 2;
        bArr[1] = 4;
        System.arraycopy(i2, 0, bArr, 2, i2.length);
        return bArr;
    }

    private static byte[] g(int i) {
        byte[] i2 = blq.i(i);
        byte[] bArr = new byte[i2.length + 2];
        bArr[0] = 3;
        bArr[1] = 4;
        System.arraycopy(i2, 0, bArr, 2, i2.length);
        return bArr;
    }

    private static byte[] k(int i) {
        byte[] i2 = blq.i(i);
        byte[] d = blq.d(i2.length);
        byte[] bArr = new byte[i2.length + 1 + d.length];
        bArr[0] = 4;
        System.arraycopy(d, 0, bArr, 1, d.length);
        System.arraycopy(i2, 0, bArr, d.length + 1, i2.length);
        return bArr;
    }

    protected byte[] e(int i, int i2) {
        byte[] o = o(i);
        byte[] l = l(i2);
        byte[] bArr = new byte[o.length + l.length];
        System.arraycopy(o, 0, bArr, 0, o.length);
        System.arraycopy(l, 0, bArr, o.length, l.length);
        return bArr;
    }

    private static byte[] o(int i) {
        byte[] g = blq.g(i);
        byte[] bArr = new byte[g.length + 2];
        bArr[0] = 1;
        bArr[1] = 1;
        System.arraycopy(g, 0, bArr, 2, g.length);
        return bArr;
    }

    private static byte[] l(int i) {
        byte[] g = blq.g(i);
        byte[] bArr = new byte[g.length + 2];
        bArr[0] = 2;
        bArr[1] = 1;
        System.arraycopy(g, 0, bArr, 2, g.length);
        return bArr;
    }

    protected byte[] c(sol solVar, int i) {
        byte[] c = c(solVar.m());
        byte[] c2 = c(solVar.u());
        byte[] a2 = a(solVar.l());
        byte[] d = d(solVar.v());
        byte[] e = e(solVar.ae());
        byte[] b = b(solVar.g());
        byte[] d2 = d(solVar.ah());
        byte[] a3 = a(solVar.h());
        byte[] e2 = e(i);
        byte[] bArr = new byte[c.length + c2.length + a2.length + d.length + e.length + b.length + d2.length + a3.length + e2.length];
        System.arraycopy(c, 0, bArr, 0, c.length);
        System.arraycopy(c2, 0, bArr, c.length, c2.length);
        System.arraycopy(a2, 0, bArr, c.length + c2.length, a2.length);
        System.arraycopy(d, 0, bArr, c.length + c2.length + a2.length, d.length);
        System.arraycopy(e, 0, bArr, c.length + c2.length + a2.length + d.length, e.length);
        System.arraycopy(b, 0, bArr, c.length + c2.length + a2.length + d.length + e.length, b.length);
        System.arraycopy(d2, 0, bArr, c.length + c2.length + a2.length + d.length + e.length + b.length, d2.length);
        System.arraycopy(a3, 0, bArr, c.length + c2.length + a2.length + d.length + e.length + b.length + d2.length, a3.length);
        System.arraycopy(e2, 0, bArr, c.length + c2.length + a2.length + d.length + e.length + b.length + d2.length + a3.length, e2.length);
        return bArr;
    }

    private static byte[] c(String str) {
        byte[] c = blq.c(str);
        byte[] d = blq.d(c.length);
        byte[] bArr = new byte[c.length + 1 + d.length];
        bArr[0] = 1;
        System.arraycopy(d, 0, bArr, 1, d.length);
        System.arraycopy(c, 0, bArr, d.length + 1, c.length);
        return bArr;
    }

    private static byte[] c(int i) {
        byte[] g = blq.g(i);
        byte[] bArr = new byte[g.length + 2];
        bArr[0] = 2;
        bArr[1] = 1;
        System.arraycopy(g, 0, bArr, 2, g.length);
        return bArr;
    }

    private static byte[] a(int i) {
        byte[] g = blq.g(i);
        byte[] bArr = new byte[g.length + 2];
        bArr[0] = 3;
        bArr[1] = 1;
        System.arraycopy(g, 0, bArr, 2, g.length);
        return bArr;
    }

    private static byte[] d(int i) {
        byte[] i2 = blq.i(i);
        byte[] bArr = new byte[i2.length + 2];
        bArr[0] = 4;
        bArr[1] = 4;
        System.arraycopy(i2, 0, bArr, 2, i2.length);
        return bArr;
    }

    private static byte[] e(String str) {
        byte[] c = blq.c(str);
        byte[] d = blq.d(c.length);
        byte[] bArr = new byte[c.length + 1 + d.length];
        bArr[0] = 8;
        System.arraycopy(d, 0, bArr, 1, d.length);
        System.arraycopy(c, 0, bArr, d.length + 1, c.length);
        return bArr;
    }

    private static byte[] b(String str) {
        byte[] c = blq.c(str);
        byte[] d = blq.d(c.length);
        byte[] bArr = new byte[c.length + 1 + d.length];
        bArr[0] = 9;
        System.arraycopy(d, 0, bArr, 1, d.length);
        System.arraycopy(c, 0, bArr, d.length + 1, c.length);
        return bArr;
    }

    private static byte[] d(String str) {
        byte[] c = blq.c(str);
        byte[] d = blq.d(c.length);
        byte[] bArr = new byte[c.length + 1 + d.length];
        bArr[0] = 10;
        System.arraycopy(d, 0, bArr, 1, d.length);
        System.arraycopy(c, 0, bArr, d.length + 1, c.length);
        return bArr;
    }

    private static byte[] a(String str) {
        byte[] c = blq.c(str);
        byte[] d = blq.d(c.length);
        byte[] bArr = new byte[c.length + 1 + d.length];
        bArr[0] = BaseType.Float;
        System.arraycopy(d, 0, bArr, 1, d.length);
        System.arraycopy(c, 0, bArr, d.length + 1, c.length);
        return bArr;
    }

    private static byte[] e(int i) {
        byte[] g = blq.g(i);
        byte[] bArr = new byte[g.length + 2];
        bArr[0] = 13;
        bArr[1] = 1;
        System.arraycopy(g, 0, bArr, 2, g.length);
        return bArr;
    }

    private static byte[] d(int i, byte b) {
        return new byte[]{(byte) i, b};
    }
}
