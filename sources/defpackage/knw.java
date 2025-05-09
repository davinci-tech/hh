package defpackage;

import com.huawei.hwfoundationmodel.trackmodel.TimeSequence;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class knw implements Serializable, TimeSequence {
    private static final long serialVersionUID = -6708196578480318759L;

    /* renamed from: a, reason: collision with root package name */
    private long f14442a;
    private float b;

    public knw() {
    }

    public knw(long j, float f) {
        this.f14442a = j;
        this.b = f;
    }

    public void a(long j) {
        this.f14442a = j;
    }

    public void b(float f) {
        this.b = f;
    }

    public float b() {
        return this.b;
    }

    @Override // com.huawei.hwfoundationmodel.trackmodel.TimeSequence
    public long acquireTime() {
        return this.f14442a;
    }
}
