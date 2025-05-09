package defpackage;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class kdr {
    private int b;
    private List<Integer> d = new ArrayList(16);

    public List<Integer> e() {
        return (List) jdy.d(this.d);
    }

    public void e(List<Integer> list) {
        this.d = (List) jdy.d(list);
    }

    public int b() {
        return ((Integer) jdy.d(Integer.valueOf(this.b))).intValue();
    }

    public void e(int i) {
        this.b = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public String toString() {
        return "[RelaxRecordIndexList :,relaxFrameCount = " + this.b + ",relaxFrameIndex = " + this.d.toString() + "]";
    }
}
