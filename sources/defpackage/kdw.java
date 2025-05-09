package defpackage;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class kdw {

    /* renamed from: a, reason: collision with root package name */
    private List<kdx> f14309a = new ArrayList(0);
    private int d;

    public void c(int i) {
        this.d = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public List<kdx> b() {
        return (List) jdy.d(this.f14309a);
    }

    public void b(List<kdx> list) {
        this.f14309a = (List) jdy.d(list);
    }

    public String toString() {
        return "[StressRecordDetails : ,stressFrameIndex = " + this.d + ",stressRecordDetails = " + this.f14309a.toString() + "]";
    }
}
