package defpackage;

import com.huawei.health.hwhealthtrackalgo.l;
import java.util.LinkedList;

/* loaded from: classes3.dex */
public final class dwv extends l {
    private dwt d;
    private LinkedList<dwl> e;

    @Override // com.huawei.health.hwhealthtrackalgo.l
    public final LinkedList<dwl> a() {
        return this.e;
    }

    @Override // com.huawei.health.hwhealthtrackalgo.a
    public final int a(dwl dwlVar) {
        if (dwlVar == null || dwlVar.c() > this.d.b()) {
            return 0;
        }
        this.e.add(dwlVar);
        if (this.e.size() > this.d.c()) {
            this.e.removeFirst();
        }
        return this.e.size() == this.d.c() ? 1 : 0;
    }

    public dwv(dwq dwqVar) {
        super(dwqVar);
        this.e = new LinkedList<>();
        this.d = this.f2496a.f();
    }
}
