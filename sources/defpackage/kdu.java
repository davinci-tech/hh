package defpackage;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class kdu {
    private List<Integer> b = new ArrayList(16);
    private int e;

    public List<Integer> e() {
        return (List) jdy.d(this.b);
    }

    public void d(List<Integer> list) {
        this.b = (List) jdy.d(list);
    }

    public int c() {
        return ((Integer) jdy.d(Integer.valueOf(this.e))).intValue();
    }

    public void e(int i) {
        this.e = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public String toString() {
        return "[StressRecordIndexList :,stressFrameCount = " + this.e + ",stressFrameIndexList = " + this.b.toString() + "]";
    }
}
