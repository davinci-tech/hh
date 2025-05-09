package com.huawei.hms.network.embedded;

import java.io.IOException;
import java.nio.channels.FileChannel;

/* loaded from: classes9.dex */
public final class o8 {

    /* renamed from: a, reason: collision with root package name */
    public final FileChannel f5400a;

    public void b(long j, bb bbVar, long j2) throws IOException {
        if (j2 < 0 || j2 > bbVar.B()) {
            throw new IndexOutOfBoundsException();
        }
        while (j2 > 0) {
            long transferFrom = this.f5400a.transferFrom(bbVar, j, j2);
            j += transferFrom;
            j2 -= transferFrom;
        }
    }

    public void a(long j, bb bbVar, long j2) throws IOException {
        if (j2 < 0) {
            throw new IndexOutOfBoundsException();
        }
        while (j2 > 0) {
            long transferTo = this.f5400a.transferTo(j, j2, bbVar);
            j += transferTo;
            j2 -= transferTo;
        }
    }

    public o8(FileChannel fileChannel) {
        this.f5400a = fileChannel;
    }
}
