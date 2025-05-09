package defpackage;

import com.huawei.health.hwhealthtrackalgo.c;
import java.util.LinkedList;

/* loaded from: classes3.dex */
public final class dwx extends c {
    private dwt d;
    private LinkedList<dwl> e;

    @Override // com.huawei.health.hwhealthtrackalgo.a
    public final int a(dwl dwlVar) {
        if (dwlVar == null) {
            return 2;
        }
        LinkedList<dwl> a2 = this.f2496a.a();
        this.e = a2;
        if (a2.size() == 0) {
            return 2;
        }
        float h = (dwlVar.h() - r1.h()) / 1000.0f;
        float d = dwu.d(this.e.getLast(), dwlVar);
        if (d >= this.d.a()) {
            return 2;
        }
        StringBuilder i = this.f2496a.i();
        i.append("; GpsTime=");
        i.append(dwlVar.h());
        i.append(",timeInterval=");
        i.append(h);
        i.append(",distanceInterval=");
        i.append(d);
        i.append(">>>FilterAlgoShortDistance");
        return 1;
    }

    public dwx(dwq dwqVar) {
        super(dwqVar);
        this.d = this.f2496a.f();
    }
}
