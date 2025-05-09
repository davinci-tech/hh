package com.huawei.agconnect.apms;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes2.dex */
public class srq extends OutputStream implements onm {
    public OutputStream abc;
    public long bcd = 0;
    public pon cde = new pon();

    public srq(OutputStream outputStream) throws IOException {
        if (outputStream == null) {
            throw new IOException("OutputStreamExtension: output stream can not be null.");
        }
        this.abc = outputStream;
    }

    @Override // com.huawei.agconnect.apms.onm
    public void abc(qpo qpoVar) {
        pon ponVar = this.cde;
        synchronized (ponVar.cde) {
            ponVar.cde.remove(qpoVar);
        }
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            this.abc.close();
            if (this.cde.cde()) {
                return;
            }
            this.cde.abc(new rqp(this, this.bcd, null));
        } catch (IOException e) {
            abc(e);
            throw e;
        }
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        try {
            this.abc.flush();
        } catch (IOException e) {
            abc(e);
            throw e;
        }
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        try {
            this.abc.write(i);
            this.bcd++;
        } catch (IOException e) {
            abc(e);
            throw e;
        }
    }

    public final void abc(Exception exc) {
        if (this.cde.cde()) {
            return;
        }
        this.cde.bcd(new rqp(this, this.bcd, exc));
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        try {
            this.abc.write(bArr);
            this.bcd += bArr.length;
        } catch (IOException e) {
            abc(e);
            throw e;
        }
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        try {
            this.abc.write(bArr, i, i2);
            this.bcd += i2;
        } catch (IOException e) {
            abc(e);
            throw e;
        }
    }
}
