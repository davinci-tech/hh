package com.huawei.hms.scankit.p;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.text.TextUtils;
import com.huawei.hms.ml.scan.HmsScan;
import com.huawei.hms.ml.scan.HmsScanBase;
import com.huawei.hms.ml.scan.HmsScanResult;
import com.huawei.hms.scankit.aiscan.common.BarcodeFormat;
import com.huawei.hms.scankit.p.w3;
import java.nio.ByteBuffer;

/* loaded from: classes9.dex */
public class r6 {

    /* renamed from: a, reason: collision with root package name */
    private static volatile r6 f5872a;

    public static r6 a() {
        if (f5872a == null) {
            synchronized (r6.class) {
                if (f5872a == null) {
                    f5872a = new r6();
                }
            }
        }
        return f5872a;
    }

    public HmsScan[] b(Bitmap bitmap, int i, boolean z, w3 w3Var) {
        w3.c cVar;
        if (w3Var != null) {
            w3Var.a("single");
            cVar = w3Var.a(z, bitmap.getHeight() * bitmap.getWidth());
            if (bitmap.getHeight() < 30 || bitmap.getWidth() < 30) {
                cVar.a(-1005);
            }
        } else {
            cVar = null;
        }
        s6[] b = m1.b(bitmap, new x6(i, z));
        o4.d("Scankit", "start totalParseResult");
        HmsScan[] a2 = y6.a(b);
        if (w3Var != null) {
            w3Var.a(a2, cVar);
        }
        return a2;
    }

    public static s6 c() {
        return new s6("", null, new u6[]{new u6(-2.0f, -2.0f), new u6(-2.0f, -2.0f), new u6(-2.0f, -2.0f), new u6(-2.0f, -2.0f)}, BarcodeFormat.NONE);
    }

    public HmsScanResult a(byte[] bArr, int i, int i2, int i3, boolean z, boolean z2, w3 w3Var) {
        w3.c cVar;
        if (w3Var != null) {
            w3Var.a("single");
            cVar = w3Var.a(z, i2 * i);
            if (i2 < 30 || i < 30) {
                cVar.a(-1005);
            }
        } else {
            cVar = null;
        }
        w3.c cVar2 = cVar;
        o4.d("Scankit", "start decodeSingleCode");
        s6[] c = m1.c(bArr, new x6(i, i2, i3, true, z));
        o4.d("Scankit", "start totalParseResult");
        HmsScan[] a2 = y6.a(c);
        o4.d("Scankit", "end totalParseResult");
        if (w3Var != null) {
            w3Var.a(a2, cVar2);
        }
        if (!z2) {
            o4.d("Scankit", "start hmsResultTrans");
            a2 = w7.a(a2);
            o4.d("Scankit", "end hmsResultTrans");
        }
        int i4 = i3 == 0 ? HmsScanBase.ALL_SCAN_TYPE : i3;
        if (r3.d) {
            return new HmsScanResult(4099, a2);
        }
        if (r3.e) {
            return new HmsScanResult(4100, a2);
        }
        if (r3.h && a2.length == 0) {
            int i5 = HmsScanBase.QRCODE_SCAN_TYPE;
            if ((i4 & i5) == i5) {
                return new HmsScanResult(4097, a2);
            }
        }
        if (a2.length == 0) {
            return new HmsScanResult(4096, a2);
        }
        if (a2.length > 0 && !TextUtils.isEmpty(a2[0].getOriginalValue())) {
            return new HmsScanResult(0, a2);
        }
        if (a2.length > 0 && a2[0].getZoomValue() > 1.0d) {
            return new HmsScanResult(4098, a2);
        }
        return new HmsScanResult(4096, new HmsScan[0]);
    }

    public static HmsScan b() {
        return new HmsScan("", HmsScanBase.FORMAT_UNKNOWN, "", HmsScan.PURE_TEXT_FORM, null, new Point[]{new Point(-2, -2), new Point(-2, -2), new Point(-2, -2), new Point(-2, -2)}, null, null).setZoomValue(1.0d);
    }

    public HmsScan[] a(Bitmap bitmap, int i, boolean z, w3 w3Var) {
        w3.c cVar;
        o4.d("Scankit", "start decodeWithBitmapWorkMulti");
        if (w3Var != null) {
            w3Var.a("multi");
            cVar = w3Var.a(z, bitmap.getHeight() * bitmap.getWidth());
            if (bitmap.getHeight() < 30 || bitmap.getWidth() < 30) {
                cVar.a(-1005);
            }
        } else {
            cVar = null;
        }
        o4.d("Scankit", "end decodeWithBitmapWorkMulti");
        s6[] a2 = m1.a(bitmap, new x6(i, z));
        o4.d("Scankit", "start totalParseResult");
        HmsScan[] a3 = y6.a(a2);
        o4.d("Scankit", "end totalParseResult");
        if (w3Var != null) {
            w3Var.a(a3, cVar);
        }
        return a3;
    }

    public HmsScan[] a(ByteBuffer byteBuffer, int i, int i2, int i3, boolean z, w3 w3Var) {
        w3.c cVar;
        if (w3Var != null) {
            w3Var.a("multi");
            int i4 = i2 * i;
            cVar = w3Var.a(z, i4);
            if (i >= 30 && i2 >= 30) {
                if (byteBuffer.array().length < i4) {
                    cVar.a(-1008);
                }
            } else {
                cVar.a(-1007);
            }
        } else {
            cVar = null;
        }
        HmsScan[] a2 = y6.a(m1.a(byteBuffer, new x6(i, i2, i3, true, z)));
        if (w3Var != null) {
            w3Var.a(a2, cVar);
        }
        return a2;
    }
}
