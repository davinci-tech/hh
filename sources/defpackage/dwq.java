package defpackage;

import com.huawei.health.hwhealthtrackalgo.c;
import com.huawei.health.hwhealthtrackalgo.i;
import com.huawei.health.hwhealthtrackalgo.l;
import com.huawei.health.hwhealthtrackalgo.stat.FilterResultListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/* loaded from: classes3.dex */
public class dwq {
    private i d;
    private dwt g;
    private l i;
    private StringBuilder l;
    private int h = 1;
    private int f = 1;

    /* renamed from: a, reason: collision with root package name */
    private ArrayList<c> f11870a = new ArrayList<>(16);
    private LinkedList<dwl> c = new LinkedList<>();
    private LinkedList<dwl> e = new LinkedList<>();
    private LinkedList<dwl> b = new LinkedList<>();
    private dxa j = new dxa();

    public void g() {
        dxa dxaVar = this.j;
        if (dxaVar != null) {
            dxaVar.i = null;
        }
    }

    public void d(l lVar) {
        this.i = lVar;
    }

    public final void b(int i) {
        if (i > 2 || i < 0) {
            this.f = 1;
        } else {
            this.f = i;
        }
    }

    public void e(i iVar) {
        this.d = iVar;
    }

    public void e(FilterResultListener filterResultListener) {
        dxa dxaVar = this.j;
        if (dxaVar != null) {
            dxaVar.i = filterResultListener;
        }
    }

    public boolean j() {
        return this.h == 1;
    }

    public StringBuilder i() {
        return this.l;
    }

    public dwt f() {
        return this.g;
    }

    public int h() {
        return this.f;
    }

    public LinkedList<dwl> e() {
        return this.b;
    }

    public LinkedList<dwl> d() {
        return this.e;
    }

    public LinkedList<dwl> a() {
        return this.c;
    }

    public void b() {
        this.b.clear();
    }

    public int b(dwl dwlVar) {
        this.j.c++;
        c(dwlVar, true);
        int size = this.e.size();
        for (int size2 = this.e.size(); size2 < size; size2++) {
            this.b.add(this.e.get(size2));
        }
        dwu.e(this.e);
        return this.b.size();
    }

    public Map<String, Integer> c() {
        dxa dxaVar = this.j;
        dxaVar.b.put("TotalPoint", Integer.valueOf(dxaVar.c));
        dxaVar.b.put("FilterAlgoAccuracy", Integer.valueOf(dxaVar.f));
        dxaVar.b.put("FilterAlgoTime", Integer.valueOf(dxaVar.e));
        dxaVar.b.put("FilterAlgoShortDistance", Integer.valueOf(dxaVar.f11872a));
        dxaVar.b.put("FilterAlgoOverSpeed", Integer.valueOf(dxaVar.d));
        dxaVar.b.put("TotalFilted", Integer.valueOf(dxaVar.h));
        return dxaVar.b;
    }

    private void l() {
        d(this.g.j() ? new dxd(this) : new dwv(this));
        e(new dwr(this));
        e(new dww(this));
        e(new dwx(this));
        e(new dws(this));
        e(this.g.h() ? new dwy(this) : new dwz(this));
    }

    private boolean d(dwl dwlVar) {
        boolean z;
        Iterator<c> it = this.f11870a.iterator();
        int i = 2;
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            }
            c next = it.next();
            int a2 = next.a(dwlVar);
            if (a2 == 1) {
                this.j.c(a2, next, dwlVar.h());
                i = a2;
                z = true;
                break;
            }
            i = a2;
        }
        if (i != 1) {
            this.j.c(i, null, dwlVar.h());
        }
        return z;
    }

    private int c(dwl dwlVar, boolean z) {
        if (this.h == 1) {
            l lVar = this.i;
            if (lVar == null || dwlVar == null) {
                return -1;
            }
            if (lVar.a(dwlVar) == 1) {
                this.h = 2;
                LinkedList<dwl> a2 = this.i.a();
                if (a2 == null) {
                    return -1;
                }
                Iterator<dwl> it = a2.iterator();
                while (it.hasNext()) {
                    c(it.next(), false);
                }
                a2.clear();
            }
        } else if (!z || !d(dwlVar)) {
            this.c.add(dwlVar);
            i iVar = this.d;
            if (iVar == null) {
                return -1;
            }
            iVar.a(dwlVar);
            dwu.e(this.c);
        }
        return 0;
    }

    private void e(c cVar) {
        this.f11870a.add(cVar);
    }

    public dwq(dwt dwtVar, StringBuilder sb, int i) {
        this.l = null;
        this.g = null;
        if (dwtVar == null || sb == null) {
            return;
        }
        this.g = dwtVar;
        this.l = sb;
        b(i);
        l();
    }
}
