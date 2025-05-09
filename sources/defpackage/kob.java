package defpackage;

import com.huawei.hwfoundationmodel.trackmodel.TimeSequence;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class kob implements Serializable, TimeSequence {
    private static final long serialVersionUID = 7816239952862604274L;

    /* renamed from: a, reason: collision with root package name */
    private int f14447a;
    private long e;

    public kob() {
    }

    public kob(long j, int i) {
        this.f14447a = i;
        this.e = j;
    }

    public int c() {
        return this.f14447a;
    }

    public void c(int i) {
        this.f14447a = i;
    }

    public void d(long j) {
        this.e = j;
    }

    @Override // com.huawei.hwfoundationmodel.trackmodel.TimeSequence
    public long acquireTime() {
        return this.e;
    }
}
