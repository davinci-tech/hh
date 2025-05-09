package defpackage;

import com.huawei.networkclient.ProgressListener;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes9.dex */
public class lqp extends InputStream {
    private ProgressListener b;
    private InputStream d;
    private long e;
    private long c = 0;

    /* renamed from: a, reason: collision with root package name */
    private long f14839a = 0;

    public lqp(InputStream inputStream, long j, ProgressListener progressListener) {
        this.d = inputStream;
        this.b = progressListener;
        this.e = j;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        return this.d.read();
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int read = this.d.read(bArr, i, i2);
        long j = this.c + (read != -1 ? read : 0L);
        this.c = j;
        if (read == -1) {
            this.b.onProgress(j, this.e, true);
        } else {
            long j2 = this.e;
            if (j2 <= 1048576 || j - this.f14839a >= 1048576) {
                this.b.onProgress(j, j2, false);
                this.f14839a = this.c;
            }
        }
        return read;
    }
}
