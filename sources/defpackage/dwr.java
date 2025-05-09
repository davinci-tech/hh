package defpackage;

import com.huawei.health.hwhealthtrackalgo.c;

/* loaded from: classes3.dex */
public final class dwr extends c {
    private dwt d;

    @Override // com.huawei.health.hwhealthtrackalgo.a
    public final int a(dwl dwlVar) {
        if (this.d == null || dwlVar.c() < this.d.b()) {
            return 2;
        }
        StringBuilder i = this.f2496a.i();
        i.append("; GpsTime=");
        i.append(dwlVar.h());
        i.append(",accuracy=");
        i.append(dwlVar.c());
        i.append(">>>FilterAlgoAccuracy");
        return 1;
    }

    public dwr(dwq dwqVar) {
        super(dwqVar);
        this.d = this.f2496a.f();
    }
}
