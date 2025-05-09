package defpackage;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class edt extends edp {
    private String c;
    private List<edo> b = new ArrayList();

    /* renamed from: a, reason: collision with root package name */
    private List<edx> f11969a = new ArrayList();
    private List<edp> e = new ArrayList();
    private int d = -1;

    public int g() {
        return this.d;
    }

    public void e(int i) {
        this.d = i;
    }

    public String e() {
        return this.c;
    }

    public void e(String str) {
        this.c = str;
    }

    public List<edo> h() {
        return this.b;
    }

    public void b(edo edoVar) {
        if (edoVar == null || !edoVar.j()) {
            return;
        }
        this.b.add(edoVar);
        this.e.add(edoVar);
    }

    public void e(edx edxVar) {
        if (edxVar == null || !edxVar.f()) {
            return;
        }
        this.f11969a.add(edxVar);
        this.e.add(edxVar);
    }

    public List<edx> j() {
        return this.f11969a;
    }

    public List<edp> d() {
        return this.e;
    }

    public void a() {
        this.b.clear();
        this.f11969a.clear();
        this.e.clear();
        this.c = "";
    }
}
