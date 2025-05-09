package defpackage;

import java.util.List;

/* loaded from: classes3.dex */
public class bjd {
    private int b;
    private List<Integer> c;
    private List<String> e;

    public int e() {
        return this.b;
    }

    public void c(int i) {
        this.b = i;
    }

    public List<Integer> d() {
        return this.c;
    }

    public void d(List<Integer> list) {
        this.c = list;
    }

    public List<String> c() {
        return this.e;
    }

    public void c(List<String> list) {
        this.e = list;
    }

    public String toString() {
        return "McuCommand{mServiceId=" + this.b + ", mCommandIds=" + this.c + ", mDestPkgNames=" + this.e + '}';
    }
}
