package com.huawei.updatesdk.a.a.b;

import java.util.ArrayDeque;
import java.util.Queue;

/* loaded from: classes7.dex */
public final class a {
    private static final a b = new a();

    /* renamed from: a, reason: collision with root package name */
    private final Queue<byte[]> f10799a = new ArrayDeque(0);

    public byte[] a() {
        byte[] poll;
        synchronized (this.f10799a) {
            poll = this.f10799a.poll();
        }
        return poll == null ? new byte[65536] : poll;
    }

    public void a(byte[] bArr) {
        if (bArr.length == 65536) {
            synchronized (this.f10799a) {
                b(bArr);
            }
        }
    }

    private void b(byte[] bArr) {
        if (this.f10799a.size() >= 32 || this.f10799a.offer(bArr)) {
            return;
        }
        com.huawei.updatesdk.a.a.c.a.a.a.a("ByteArrayPool", "releaseBytes false");
    }

    public static a b() {
        return b;
    }

    private a() {
    }
}
