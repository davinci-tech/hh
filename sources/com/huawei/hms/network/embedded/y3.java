package com.huawei.hms.network.embedded;

import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.StringUtils;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: classes9.dex */
public class y3 extends OutputStream {
    public static final double DEFAULT_BUFFER_SIZE = 16375.0d;
    public static final String b = "CronetOutputStream";

    /* renamed from: a, reason: collision with root package name */
    public final LinkedBlockingQueue<Object> f5580a;

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        a(bArr, i, i2);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        write(StringUtils.getBytes(String.valueOf(i)));
    }

    private void a(byte[] bArr, int i, int i2) {
        int i3 = i2 - i;
        try {
            int ceil = (int) Math.ceil(i3 / 16375.0d);
            byte[][] bArr2 = new byte[ceil][];
            for (int i4 = 0; i4 < ceil; i4++) {
                int i5 = (int) (i4 * 16375.0d);
                int i6 = (int) (i5 + 16375.0d);
                if (i6 > i3) {
                    i6 = i3;
                }
                bArr2[i4] = Arrays.copyOfRange(bArr, i5, i6);
                Logger.d("CronetOutputStream", "wirte into linkedBlockingQueue");
                this.f5580a.put(bArr2[i4]);
            }
        } catch (InterruptedException unused) {
            Logger.w("CronetOutputStream", "An error occurred when cronet upload data or split byte.");
        }
    }

    public y3(LinkedBlockingQueue<Object> linkedBlockingQueue) {
        this.f5580a = linkedBlockingQueue;
    }
}
