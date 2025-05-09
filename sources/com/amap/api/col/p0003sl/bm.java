package com.amap.api.col.p0003sl;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/* loaded from: classes2.dex */
final class bm {

    /* renamed from: a, reason: collision with root package name */
    RandomAccessFile f924a;

    public bm() throws IOException {
        this("", 0L);
    }

    public bm(String str, long j) throws IOException {
        File file = new File(str);
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
            } catch (IOException e) {
                iv.c(e, "FileAccessI", "create");
                e.printStackTrace();
            }
        }
        RandomAccessFile randomAccessFile = new RandomAccessFile(str, "rw");
        this.f924a = randomAccessFile;
        randomAccessFile.seek(j);
    }

    public final int a(byte[] bArr) throws IOException {
        int length;
        synchronized (this) {
            this.f924a.write(bArr);
            length = bArr.length;
        }
        return length;
    }

    public final void a() {
        RandomAccessFile randomAccessFile = this.f924a;
        if (randomAccessFile != null) {
            try {
                randomAccessFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.f924a = null;
        }
    }
}
