package defpackage;

import com.huawei.health.hwhealthtrackalgo.l;
import java.util.LinkedList;

/* loaded from: classes3.dex */
public final class dxd extends l {
    private dwt d;
    private LinkedList<dwl> e;

    @Override // com.huawei.health.hwhealthtrackalgo.l
    public final LinkedList<dwl> a() {
        return this.e;
    }

    @Override // com.huawei.health.hwhealthtrackalgo.a
    public final int a(dwl dwlVar) {
        if (dwlVar == null) {
            return 0;
        }
        int h = this.f2496a.h();
        if (dwlVar.c() > this.d.b()) {
            return 0;
        }
        if (this.e.size() > 0 && dwlVar.h() - this.e.getLast().h() > this.d.g()) {
            this.e.clear();
        }
        this.e.add(dwlVar);
        if (this.e.size() > this.d.c()) {
            this.e.removeFirst();
        }
        if (this.e.size() != this.d.c()) {
            return 0;
        }
        int i = 0;
        while (i < this.d.c() - 1) {
            dwl dwlVar2 = this.e.get(i);
            i++;
            if (dwu.e(dwlVar2, this.e.get(i)) > this.d.a(h)) {
                return 0;
            }
        }
        StringBuilder i2 = this.f2496a.i();
        i2.append("; GpsTime=");
        i2.append(dwlVar.h());
        i2.append(">>>StartAlgoTriple find start");
        return 1;
    }

    public dxd(dwq dwqVar) {
        super(dwqVar);
        this.e = new LinkedList<>();
        this.d = this.f2496a.f();
    }
}
