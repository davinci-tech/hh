package com.amap.api.col.p0003sl;

import com.amap.api.col.p0003sl.ka;

/* loaded from: classes2.dex */
public class jv {

    /* renamed from: a, reason: collision with root package name */
    private jx f1232a;
    private ka b;
    private long c;
    private long d;

    public interface a {
        void onDownload(byte[] bArr, long j);

        void onException(Throwable th);

        void onFinish();

        void onStop();
    }

    public jv(ka kaVar) {
        this(kaVar, (byte) 0);
    }

    private jv(ka kaVar, byte b) {
        this(kaVar, 0L, -1L, false);
    }

    public jv(ka kaVar, long j, long j2, boolean z) {
        this.b = kaVar;
        this.c = j;
        this.d = j2;
        kaVar.setHttpProtocol(z ? ka.c.HTTPS : ka.c.HTTP);
        this.b.setDegradeAbility(ka.a.SINGLE);
    }

    public final void a(a aVar) {
        try {
            jx jxVar = new jx();
            this.f1232a = jxVar;
            jxVar.b(this.d);
            this.f1232a.a(this.c);
            jt.a();
            if (jt.b(this.b)) {
                this.b.setDegradeType(ka.b.NEVER_GRADE);
                this.f1232a.a(this.b, aVar);
            } else {
                this.b.setDegradeType(ka.b.DEGRADE_ONLY);
                this.f1232a.a(this.b, aVar);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void a() {
        jx jxVar = this.f1232a;
        if (jxVar != null) {
            jxVar.a();
        }
    }
}
