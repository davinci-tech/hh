package defpackage;

import com.huawei.hwfoundationmodel.trackmodel.TimeSequence;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class koc implements Serializable, TimeSequence {
    private static final long serialVersionUID = 4068195580731601439L;

    /* renamed from: a, reason: collision with root package name */
    private long f14448a;
    private int b;

    public koc() {
    }

    public koc(long j, int i) {
        this.b = i;
        this.f14448a = j;
    }

    public int b() {
        return this.b;
    }

    public void c(int i) {
        this.b = i;
    }

    public void c(long j) {
        this.f14448a = j;
    }

    @Override // com.huawei.hwfoundationmodel.trackmodel.TimeSequence
    public long acquireTime() {
        return this.f14448a;
    }
}
