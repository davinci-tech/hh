package defpackage;

import com.huawei.health.hwhealthtrackalgo.c;
import java.util.LinkedList;

/* loaded from: classes3.dex */
public final class dww extends c {
    private LinkedList<dwl> d;
    private dwt e;

    @Override // com.huawei.health.hwhealthtrackalgo.a
    public final int a(dwl dwlVar) {
        if (dwlVar != null && this.e != null) {
            LinkedList<dwl> a2 = this.f2496a.a();
            this.d = a2;
            if (a2.size() == 0) {
                return 2;
            }
            float h = (dwlVar.h() - this.d.getLast().h()) / 1000.0f;
            if (h < this.e.f()) {
                StringBuilder i = this.f2496a.i();
                i.append("; GpsTime=");
                i.append(dwlVar.h());
                i.append(",timeInterval=");
                i.append(h);
                i.append(">>>FilterAlgoTime");
                return 1;
            }
        }
        return 2;
    }

    public dww(dwq dwqVar) {
        super(dwqVar);
        this.e = this.f2496a.f();
    }
}
