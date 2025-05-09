package com.huawei.haf.common.os;

import health.compact.a.LogUtil;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;

/* loaded from: classes.dex */
public final class FileLockHelper implements Closeable {

    /* renamed from: a, reason: collision with root package name */
    private final RandomAccessFile f2096a;
    private final FileLock b;
    private volatile boolean c;

    private FileLockHelper(File file, boolean z) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, z ? "rws" : "rw");
        try {
            FileLock c = c(file, randomAccessFile);
            if (c == null) {
                FileUtils.d(randomAccessFile);
                randomAccessFile = null;
            }
            this.f2096a = randomAccessFile;
            this.b = c;
        } catch (Throwable th) {
            FileUtils.d(randomAccessFile);
            throw th;
        }
    }

    private FileLock c(File file, RandomAccessFile randomAccessFile) throws IOException {
        FileLock fileLock = null;
        Throwable e = null;
        for (int i = 0; i < 3; i++) {
            try {
                fileLock = randomAccessFile.getChannel().lock();
                if (fileLock != null) {
                    break;
                }
            } catch (IOException | OverlappingFileLockException e2) {
                e = e2;
                LogUtil.e("HAF_FileLockHelper", "getFileLock failed time:", 10, ", ex=", LogUtil.a(e));
            }
            if (i < 3) {
                try {
                    Thread.sleep(10L);
                } catch (InterruptedException e3) {
                    LogUtil.e("HAF_FileLockHelper", "getFileLock Thread sleep ex=", LogUtil.a(e3));
                }
            }
        }
        if (fileLock != null) {
            return fileLock;
        }
        throw new IOException("FileLockHelper lock file failed: " + file.getPath(), e);
    }

    public static FileLockHelper b(File file) throws IOException {
        return new FileLockHelper(file, false);
    }

    public static FileLockHelper c(File file) throws IOException {
        return new FileLockHelper(file, true);
    }

    public boolean c() {
        FileLock fileLock = this.b;
        if (fileLock == null) {
            return false;
        }
        return fileLock.isValid();
    }

    public void c(byte[] bArr) throws IOException {
        this.f2096a.seek(0L);
        this.f2096a.write(bArr);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.c) {
            return;
        }
        this.c = true;
        FileUtils.c(this.b);
        FileUtils.d(this.f2096a);
    }
}
