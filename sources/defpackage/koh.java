package defpackage;

import com.huawei.hwfoundationmodel.trackmodel.TimeSequence;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class koh implements Serializable, TimeSequence {
    private static final long serialVersionUID = 4279729944497991314L;
    private long c;
    private int e;

    public koh(long j, int i) {
        this.c = j;
        this.e = i;
    }

    public int a() {
        return this.e;
    }

    public String toString() {
        return this.c + ":" + this.e;
    }

    @Override // com.huawei.hwfoundationmodel.trackmodel.TimeSequence
    public long acquireTime() {
        return this.c;
    }
}
