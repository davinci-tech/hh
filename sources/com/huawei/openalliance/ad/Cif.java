package com.huawei.openalliance.ad;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/* renamed from: com.huawei.openalliance.ad.if, reason: invalid class name */
/* loaded from: classes5.dex */
class Cif {
    static void b(File file) {
        long length = file.length();
        if (length < 1) {
            length = 0;
        }
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rwd");
        long j = length - 1;
        randomAccessFile.seek(j);
        byte readByte = randomAccessFile.readByte();
        randomAccessFile.seek(j);
        randomAccessFile.write(readByte);
        randomAccessFile.close();
    }

    static void a(File file) {
        long currentTimeMillis = System.currentTimeMillis();
        long lastModified = file.lastModified();
        if (lastModified == currentTimeMillis) {
            currentTimeMillis++;
        }
        if (file.setLastModified(currentTimeMillis)) {
            return;
        }
        try {
            b(file);
        } catch (IOException e) {
            ho.d("DiskFiles", "Failed to manually update lastModifyTime to file %s with error %s", file.getName(), e.getClass().getSimpleName());
        }
        if (lastModified < currentTimeMillis) {
            ho.c("DiskFiles", "Failed to manually update lastModifyTime to file %s", file.getName());
        }
    }
}
