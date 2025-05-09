package defpackage;

import com.huawei.hwfoundationmodel.trackmodel.TimeSequence;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class koi implements Serializable, TimeSequence {
    private static final long serialVersionUID = 3734895889084885412L;
    private long c;
    private double d;

    public koi(long j, double d) {
        this.c = j;
        this.d = d;
    }

    public double e() {
        return this.d;
    }

    public String toString() {
        return this.c + ":" + this.d;
    }

    @Override // com.huawei.hwfoundationmodel.trackmodel.TimeSequence
    public long acquireTime() {
        return this.c;
    }
}
