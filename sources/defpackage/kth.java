package defpackage;

import java.util.List;

/* loaded from: classes5.dex */
public class kth {
    private List<Float> c;
    private int d;

    public kth(List<Float> list, int i) {
        this.c = list;
        this.d = i;
    }

    public int b() {
        return this.d;
    }

    public List<Float> d() {
        return this.c;
    }

    public String toString() {
        return "mStatusNum = " + this.d + " mSignalList = " + this.c.toString();
    }
}
