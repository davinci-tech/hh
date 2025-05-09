package com.huawei.hms.scankit.p;

import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.RemoteException;
import com.huawei.hms.feature.dynamic.IObjectWrapper;
import com.huawei.hms.feature.dynamic.ObjectWrapper;
import com.huawei.hms.hmsscankit.DetailRect;
import com.huawei.hms.hmsscankit.api.IRemoteFrameDecoderDelegate;
import com.huawei.hms.scankit.util.OpencvJNI;
import com.huawei.watchface.videoedit.gles.Constant;

/* loaded from: classes9.dex */
public class h4 extends IRemoteFrameDecoderDelegate.Stub {
    private static volatile h4 d = new h4();

    /* renamed from: a, reason: collision with root package name */
    Point f5788a;
    int b = 0;
    Rect c;

    private h4() {
    }

    public static h4 a() {
        return d;
    }

    public Rect b(int i, int i2) {
        synchronized (this) {
            Rect rect = new Rect(a(i, i2));
            Point point = new Point(i, i2);
            Point point2 = this.f5788a;
            if (point2 == null) {
                return null;
            }
            int i3 = point2.x;
            int i4 = point2.y;
            if (i3 < i4) {
                int i5 = rect.left;
                int i6 = point.y;
                rect.left = (i5 * i6) / i3;
                rect.right = (rect.right * i6) / i3;
                int i7 = rect.top;
                int i8 = point.x;
                rect.top = (i7 * i8) / i4;
                rect.bottom = (rect.bottom * i8) / i4;
            } else {
                int i9 = rect.top;
                int i10 = point.y;
                rect.top = (i9 * i10) / i4;
                rect.bottom = (rect.bottom * i10) / i4;
                int i11 = rect.left;
                int i12 = point.x;
                rect.left = (i11 * i12) / i3;
                rect.right = (rect.right * i12) / i3;
            }
            return rect;
        }
    }

    @Override // com.huawei.hms.hmsscankit.api.IRemoteFrameDecoderDelegate
    public s6[] decode(byte[] bArr, int i, int i2, int i3, int i4, IObjectWrapper iObjectWrapper) throws RemoteException {
        boolean z;
        if (!r3.A) {
            OpencvJNI.init();
        }
        boolean z2 = false;
        boolean z3 = true;
        if (iObjectWrapper == null || !(ObjectWrapper.unwrap(iObjectWrapper) instanceof Bundle)) {
            z = false;
        } else {
            Bundle bundle = (Bundle) ObjectWrapper.unwrap(iObjectWrapper);
            this.f5788a = (Point) bundle.getParcelable("Screen");
            this.c = (Rect) bundle.getParcelable("Rect");
            z = ((Bundle) ObjectWrapper.unwrap(iObjectWrapper)).getBoolean(DetailRect.USE_APK, false);
            z2 = ((Bundle) ObjectWrapper.unwrap(iObjectWrapper)).getBoolean(DetailRect.SUPPORT_ROLLBACK, false);
            z3 = ((Bundle) ObjectWrapper.unwrap(iObjectWrapper)).getBoolean(DetailRect.PARSE_RESULT, true);
        }
        r3.f = z3;
        if (z2 && !r3.f5870a && z) {
            return new s6[]{r6.c()};
        }
        if (this.c == null) {
            this.c = new Rect(-1, -1, -1, -1);
        }
        if (this.f5788a == null) {
            this.f5788a = new Point(1080, Constant.FBO_HEIGHT);
        }
        e6 a2 = a(bArr, i, i2, i3);
        byte[] b = a2.b();
        x6 x6Var = new x6(a2.c(), a2.a(), i4);
        int i5 = this.b;
        this.b = i5 + 1;
        return m1.c(b, x6Var.a(i5));
    }

    private e6 a(byte[] bArr, int i, int i2, int i3) {
        if (i3 == 0) {
            byte[] bArr2 = new byte[bArr.length];
            for (int i4 = 0; i4 < i2; i4++) {
                for (int i5 = 0; i5 < i; i5++) {
                    bArr2[(((i5 * i2) + i2) - i4) - 1] = bArr[(i4 * i) + i5];
                }
            }
            return a(bArr2, i2, i);
        }
        if (i3 == 2) {
            byte[] bArr3 = new byte[bArr.length];
            for (int i6 = 0; i6 < i2; i6++) {
                for (int i7 = 0; i7 < i; i7++) {
                    bArr3[(((i - 1) - i7) * i2) + i6] = bArr[(i6 * i) + i7];
                }
            }
            return a(bArr3, i2, i);
        }
        if (i3 != 3) {
            return a(bArr, i, i2);
        }
        byte[] bArr4 = new byte[bArr.length];
        for (int i8 = 0; i8 < i2; i8++) {
            for (int i9 = 0; i9 < i; i9++) {
                bArr4[(((((i2 - 1) - i8) * i) + i) - 1) - i9] = bArr[(i8 * i) + i9];
            }
        }
        return a(bArr4, i, i2);
    }

    public Rect a(int i, int i2) {
        Rect rect;
        synchronized (this) {
            int min = Math.min(i, i2);
            int i3 = (i - min) / 2;
            int i4 = (i2 - min) / 2;
            rect = new Rect(i3, i4, i3 + min, min + i4);
        }
        return rect;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00d3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.huawei.hms.scankit.p.e6 a(byte[] r12, int r13, int r14) {
        /*
            Method dump skipped, instructions count: 232
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.scankit.p.h4.a(byte[], int, int):com.huawei.hms.scankit.p.e6");
    }
}
