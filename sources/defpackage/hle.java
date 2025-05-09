package defpackage;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class hle extends hld {
    private List<hjd> c = null;
    private boolean b = false;
    private List<Integer> d = null;

    public void e(List<hjd> list) {
        if (this.c == null) {
            this.c = new ArrayList(16);
        }
        this.c.addAll(list);
    }

    public List<hjd> i() {
        return this.c;
    }

    public boolean j() {
        return this.b;
    }

    public List<Integer> f() {
        return this.d;
    }
}
