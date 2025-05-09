package defpackage;

import com.huawei.hwfoundationmodel.trackmodel.TimeSequence;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class kog implements Serializable, TimeSequence {
    private static final long serialVersionUID = 7290174584790198637L;
    private long c;
    private int d;

    public kog(long j, int i) {
        this.c = j;
        this.d = i;
    }

    public int d() {
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
