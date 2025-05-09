package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.jk;

/* loaded from: classes5.dex */
public abstract class na<V extends jk> extends jj<V> implements oi<V> {
    protected Context d;
    protected cs e;
    protected Integer f;
    protected String g;

    @Override // com.huawei.openalliance.ad.oi
    public void i() {
        this.b.f();
    }

    @Override // com.huawei.openalliance.ad.oi
    public void h() {
        this.b.c();
    }

    @Override // com.huawei.openalliance.ad.oi
    public void c(long j, long j2, long j3, long j4) {
        this.b.b(j, j2, (int) j3, (int) j4);
    }

    @Override // com.huawei.openalliance.ad.oi
    public void b(long j, long j2, long j3, long j4) {
        this.b.c(j, j2, (int) j3, (int) j4);
    }

    @Override // com.huawei.openalliance.ad.oi
    public void a(final long j, final long j2, final long j3) {
        if (j == 0 || j >= j3 || this.e == null) {
            return;
        }
        com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.na.1
            @Override // java.lang.Runnable
            public void run() {
                long j4 = j3;
                long j5 = j;
                long j6 = j2;
                long j7 = 0;
                if (j6 != 0 && j6 < j4) {
                    j7 = j4 - j6;
                }
                com.huawei.openalliance.ad.analysis.a aVar = new com.huawei.openalliance.ad.analysis.a();
                aVar.a(na.this.f);
                aVar.d(na.this.g);
                na.this.e.a(na.this.f7126a, j4 - j5, j7, aVar);
            }
        });
    }

    public na(Context context) {
        this.d = context;
        this.e = new com.huawei.openalliance.ad.analysis.c(context);
    }
}
