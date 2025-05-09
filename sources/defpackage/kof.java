package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwfoundationmodel.trackmodel.TimeSequence;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class kof implements Serializable, TimeSequence {
    private static final long serialVersionUID = -1245764951606553008L;

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("mTime")
    private long f14449a;

    @SerializedName("mSpo2")
    private int b;

    public kof(long j, int i) {
        this.f14449a = j;
        this.b = i;
    }

    @Override // com.huawei.hwfoundationmodel.trackmodel.TimeSequence
    public long acquireTime() {
        return this.f14449a;
    }

    public int b() {
        return this.b;
    }
}
