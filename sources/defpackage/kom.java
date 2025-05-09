package defpackage;

import com.huawei.hwfoundationmodel.trackmodel.TimeSequence;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class kom implements Serializable, TimeSequence {
    private static final long serialVersionUID = -5697764361487114883L;

    /* renamed from: a, reason: collision with root package name */
    private int f14451a;
    private long d;

    public kom() {
    }

    public kom(long j, int i) {
        this.f14451a = i;
        this.d = j;
    }

    public int d() {
        return this.f14451a;
    }

    public void d(int i) {
        this.f14451a = i;
    }

    public void b(long j) {
        this.d = j;
    }

    @Override // com.huawei.hwfoundationmodel.trackmodel.TimeSequence
    public long acquireTime() {
        return this.d;
    }
}
