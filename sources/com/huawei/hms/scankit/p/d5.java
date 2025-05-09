package com.huawei.hms.scankit.p;

import com.huawei.hms.scankit.util.LoadOpencvJNIUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class d5 {

    /* renamed from: a, reason: collision with root package name */
    public List<i2> f5762a = new ArrayList();
    private int b = 0;

    public void a(boolean z, byte[] bArr, int i, int i2, int i3, boolean z2) {
        float[] multiBarcodeDetect = LoadOpencvJNIUtil.multiBarcodeDetect(bArr, i, i2, i3, z2);
        if (multiBarcodeDetect != null) {
            this.b = multiBarcodeDetect.length / 10;
        } else {
            this.b = 0;
        }
        for (int i4 = 0; i4 < this.b; i4++) {
            int i5 = i4 * 10;
            if (w7.a(multiBarcodeDetect, i5)) {
                int i6 = i5 + 1;
                if (w7.a(multiBarcodeDetect, i6)) {
                    int i7 = i5 + 2;
                    if (w7.a(multiBarcodeDetect, i7)) {
                        int i8 = i5 + 3;
                        if (w7.a(multiBarcodeDetect, i8)) {
                            int i9 = i5 + 4;
                            if (w7.a(multiBarcodeDetect, i9)) {
                                int i10 = i5 + 5;
                                if (w7.a(multiBarcodeDetect, i10)) {
                                    int i11 = i5 + 6;
                                    if (w7.a(multiBarcodeDetect, i11)) {
                                        int i12 = i5 + 7;
                                        if (w7.a(multiBarcodeDetect, i12)) {
                                            int i13 = i5 + 8;
                                            if (w7.a(multiBarcodeDetect, i13)) {
                                                int i14 = i5 + 9;
                                                if (w7.a(multiBarcodeDetect, i14)) {
                                                    this.f5762a.add(new i2(z, multiBarcodeDetect[i5], multiBarcodeDetect[i6], multiBarcodeDetect[i7], multiBarcodeDetect[i8], multiBarcodeDetect[i9], multiBarcodeDetect[i10], multiBarcodeDetect[i11], multiBarcodeDetect[i12], multiBarcodeDetect[i13], multiBarcodeDetect[i14]));
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
