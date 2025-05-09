package com.amap.api.col.p0003sl;

import java.util.List;

/* loaded from: classes2.dex */
public final class lh extends le {
    private static lh b = new lh();

    public static lh b() {
        return b;
    }

    private lh() {
        super(5120);
    }

    public final byte[] a(byte[] bArr, byte[] bArr2, List<? extends ll> list) {
        if (list == null) {
            return null;
        }
        try {
            int size = list.size();
            if (size <= 0 || bArr == null) {
                return null;
            }
            a();
            int a2 = lo.a((na) this.f1322a, bArr);
            int[] iArr = new int[size];
            for (int i = 0; i < size; i++) {
                ll llVar = list.get(i);
                iArr[i] = lt.a(this.f1322a, (byte) llVar.a(), lt.a(this.f1322a, llVar.b()));
            }
            this.f1322a.c(lo.a(this.f1322a, a2, bArr2 != null ? lo.b(this.f1322a, bArr2) : 0, lo.a(this.f1322a, iArr)));
            return this.f1322a.c();
        } catch (Throwable th) {
            ms.a(th);
            return null;
        }
    }

    public final byte[] c() {
        super.a();
        try {
            this.f1322a.c(mr.a(this.f1322a, mq.a(), this.f1322a.a(mq.f()), this.f1322a.a(mq.c()), (byte) mq.m(), this.f1322a.a(mq.i()), this.f1322a.a(mq.h()), this.f1322a.a(a(mq.g())), this.f1322a.a(a(mq.j())), mp.a(mq.n()), this.f1322a.a(mq.l()), this.f1322a.a(mq.k()), this.f1322a.a(mq.d()), this.f1322a.a(mq.e())));
            return this.f1322a.c();
        } catch (Exception e) {
            ms.a(e);
            return null;
        }
    }

    private static String a(String str) {
        return str == null ? "" : str;
    }
}
