package defpackage;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class nzd extends nzc {
    private static final long serialVersionUID = -374428058867356358L;
    private List<nzn> b = new ArrayList(8);
    private int e;

    public void d(int i) {
        this.e = i;
    }

    public List<nzn> b() {
        return this.b;
    }

    public void b(nzn nznVar) {
        this.b.add(nznVar);
    }

    public String toString() {
        return "Content{partCount=" + this.e + ", parts=" + this.b + '}';
    }
}
