package defpackage;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class kdp {
    private List<kds> b = new ArrayList(0);
    private int e;

    public void b(int i) {
        this.e = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public List<kds> d() {
        return (List) jdy.d(this.b);
    }

    public void d(List<kds> list) {
        this.b = (List) jdy.d(list);
    }

    public String toString() {
        return "[RelaxRecordDetails : ,relaxFrameIndex = " + this.e + ",relaxRecordDetails = " + this.b.toString() + "]";
    }
}
