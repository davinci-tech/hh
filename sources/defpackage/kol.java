package defpackage;

import com.huawei.hwfoundationmodel.trackmodel.TimeSequence;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class kol implements Serializable, TimeSequence {
    private static final long serialVersionUID = -1245764951606553008L;

    /* renamed from: a, reason: collision with root package name */
    private long f14450a;
    private int e;

    public kol(long j, int i) {
        this.f14450a = j;
        this.e = i;
    }

    @Override // com.huawei.hwfoundationmodel.trackmodel.TimeSequence
    public long acquireTime() {
        return this.f14450a;
    }

    public int c() {
        return this.e;
    }

    public String toString() {
        return this.f14450a + ":" + this.e;
    }
}
