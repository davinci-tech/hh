package com.huawei.hms.mlsdk.asr.engine.utils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/* loaded from: classes4.dex */
public class a {
    private int b;

    /* renamed from: a, reason: collision with root package name */
    private Queue<byte[]> f5088a = new LinkedList();
    private int c = 0;
    private byte[] d = null;

    public a(int i) {
        this.b = i;
    }

    public void a(byte[] bArr) {
        int i;
        try {
            int length = bArr.length;
            if (length == 0) {
                return;
            }
            if (this.c == 0) {
                int i2 = this.b;
                this.d = new byte[i2];
                this.c = i2;
            }
            int i3 = this.c;
            if (i3 >= length) {
                System.arraycopy(bArr, 0, this.d, this.b - i3, length);
                int i4 = this.c - length;
                this.c = i4;
                if (i4 != 0 || this.f5088a.offer(this.d)) {
                    return;
                }
                SmartLogger.e("AudioBlockSplit", "offer failed");
                return;
            }
            int i5 = this.b;
            int i6 = i3 / i5;
            if (i3 % i5 != 0) {
                i6++;
            }
            int i7 = 0;
            for (int i8 = 0; i8 < i6; i8++) {
                if (i8 != i6 - 1 || (i = this.c) <= 0) {
                    int i9 = this.b;
                    byte[] bArr2 = new byte[i9];
                    this.d = bArr2;
                    int i10 = i8 * i9;
                    System.arraycopy(bArr, i10, bArr2, 0, i9);
                    i7 = i10;
                } else {
                    this.d = Arrays.copyOfRange(bArr, i7, i);
                }
                if (!this.f5088a.offer(this.d)) {
                    SmartLogger.e("AudioBlockSplit", "offer failed");
                }
            }
            this.c = 0;
        } catch (ArrayIndexOutOfBoundsException e) {
            StringBuilder a2 = com.huawei.hms.mlsdk.asr.o.a.a("setInput error");
            a2.append(e.getMessage());
            SmartLogger.e("AudioBlockSplit", a2.toString());
        }
    }

    public byte[] b() {
        return this.f5088a.size() == 0 ? new byte[0] : this.f5088a.poll();
    }

    public int c() {
        return this.f5088a.size();
    }

    public void a() {
        this.f5088a.clear();
    }
}
