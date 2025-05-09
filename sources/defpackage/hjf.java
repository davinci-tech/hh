package defpackage;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class hjf {
    private int b;
    private List<hjd> d;
    private List<Integer> e;

    public hjf() {
        this.e = new ArrayList();
        this.d = new ArrayList();
    }

    public hjf(int i, List<Integer> list, List<hjd> list2) {
        this.e = new ArrayList();
        new ArrayList();
        this.b = i;
        this.e = list;
        this.d = list2;
    }

    public int e() {
        return this.b;
    }

    public void b(int i) {
        this.b = i;
    }

    public List<Integer> a() {
        return this.e;
    }

    public void c(List<Integer> list) {
        this.e = list;
    }

    public List<hjd> d() {
        return this.d;
    }

    public void d(List<hjd> list) {
        this.d = list;
    }
}
