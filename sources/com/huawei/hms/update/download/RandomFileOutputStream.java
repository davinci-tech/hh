package com.huawei.hms.update.download;

import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.IOUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;

/* loaded from: classes9.dex */
public class RandomFileOutputStream extends OutputStream {

    /* renamed from: a, reason: collision with root package name */
    private RandomAccessFile f6042a;

    public RandomFileOutputStream(File file, int i) {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rwd");
            this.f6042a = randomAccessFile;
            randomAccessFile.setLength(i);
        } catch (FileNotFoundException unused) {
            HMSLog.e("RandomFileOutputStream", "create  file stream failed");
        } catch (IOException unused2) {
            IOUtils.closeQuietly(this.f6042a);
            HMSLog.e("RandomFileOutputStream", "create  file stream failed");
        }
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        RandomAccessFile randomAccessFile = this.f6042a;
        if (randomAccessFile != null) {
            randomAccessFile.close();
        }
    }

    public void seek(long j) throws IOException {
        RandomAccessFile randomAccessFile = this.f6042a;
        if (randomAccessFile != null) {
            randomAccessFile.seek(j);
        }
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        RandomAccessFile randomAccessFile = this.f6042a;
        if (randomAccessFile != null) {
            randomAccessFile.write(bArr, i, i2);
        }
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        write(new byte[]{(byte) i}, 0, 1);
    }
}
