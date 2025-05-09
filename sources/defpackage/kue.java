package defpackage;

import com.huawei.hwnetworkmodel.TrafficMonitoringService;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes5.dex */
public class kue extends InputStream {

    /* renamed from: a, reason: collision with root package name */
    private InputStream f14598a;
    private final Object e = new Object();

    public kue(InputStream inputStream) {
        this.f14598a = inputStream;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        InputStream inputStream = this.f14598a;
        if (inputStream == null) {
            throw new IOException("read no param on a null InputStream");
        }
        int read = inputStream.read();
        if (read > 0) {
            TrafficMonitoringService.e(read);
        }
        return read;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        InputStream inputStream = this.f14598a;
        if (inputStream == null) {
            throw new IOException("read one param on a null InputStream");
        }
        int read = inputStream.read(bArr);
        if (read > 0) {
            TrafficMonitoringService.e(read);
        }
        return read;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        InputStream inputStream = this.f14598a;
        if (inputStream == null) {
            throw new IOException("read three params on a null InputStream");
        }
        int read = inputStream.read(bArr, i, i2);
        if (read > 0) {
            TrafficMonitoringService.e(read);
        }
        return read;
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        InputStream inputStream = this.f14598a;
        if (inputStream == null) {
            throw new IOException("available on a null InputStream");
        }
        return inputStream.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        InputStream inputStream = this.f14598a;
        if (inputStream == null) {
            throw new IOException("close on a null InputStream");
        }
        inputStream.close();
    }

    @Override // java.io.InputStream
    public void mark(int i) {
        InputStream inputStream = this.f14598a;
        if (inputStream == null) {
            return;
        }
        inputStream.mark(i);
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        InputStream inputStream = this.f14598a;
        if (inputStream == null) {
            return false;
        }
        return inputStream.markSupported();
    }

    @Override // java.io.InputStream
    public void reset() throws IOException {
        synchronized (this.e) {
            InputStream inputStream = this.f14598a;
            if (inputStream == null) {
                throw new IOException("reset on a null InputStream");
            }
            inputStream.reset();
        }
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        InputStream inputStream = this.f14598a;
        if (inputStream == null) {
            throw new IOException("skip on a null InputStream");
        }
        return inputStream.skip(j);
    }
}
