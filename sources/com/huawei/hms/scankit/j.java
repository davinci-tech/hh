package com.huawei.hms.scankit;

import com.huawei.health.R;
import com.huawei.hms.scankit.p.j0;
import com.huawei.hms.scankit.p.o4;

/* loaded from: classes9.dex */
public class j implements j0.e {

    /* renamed from: a, reason: collision with root package name */
    private d f5725a;

    public j(d dVar) {
        this.f5725a = dVar;
    }

    @Override // com.huawei.hms.scankit.p.j0.e
    public void a(byte[] bArr) {
        o4.a("scan-time", "request frame time:" + System.currentTimeMillis());
        this.f5725a.a().obtainMessage(R.id.scankit_decode, bArr).sendToTarget();
    }
}
