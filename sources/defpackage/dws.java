package defpackage;

import com.huawei.health.hwhealthtrackalgo.c;
import java.util.LinkedList;

/* loaded from: classes3.dex */
public final class dws extends c {
    private dwt d;
    private LinkedList<dwl> e;

    @Override // com.huawei.health.hwhealthtrackalgo.a
    public final int a(dwl dwlVar) {
        if (dwlVar == null || this.d == null) {
            return 2;
        }
        int h = this.f2496a.h();
        LinkedList<dwl> a2 = this.f2496a.a();
        this.e = a2;
        if (a2.size() == 0) {
            return 2;
        }
        float e = dwu.e(this.e.getLast(), dwlVar);
        boolean z = dwlVar.b() > this.d.e() && e > this.d.d() * dwlVar.b();
        if (e <= this.d.a(h) && !z) {
            return 2;
        }
        StringBuilder i = this.f2496a.i();
        i.append("; GpsTime=");
        i.append(dwlVar.h());
        i.append(",GpsSpeed=");
        i.append(dwlVar.b());
        i.append(",speed=");
        i.append(e);
        i.append(">>>FilterAlgoOverSpeed");
        return 1;
    }

    public dws(dwq dwqVar) {
        super(dwqVar);
        this.d = this.f2496a.f();
    }
}
