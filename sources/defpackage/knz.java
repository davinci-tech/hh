package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwfoundationmodel.trackmodel.TimeSequence;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class knz implements Serializable, TimeSequence {
    private static final long serialVersionUID = -2003020627619347417L;

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("mAltitude")
    private double f14444a;

    @SerializedName("mTime")
    private long e;

    public knz(long j, double d) {
        this.e = j;
        this.f14444a = d;
    }

    @Override // com.huawei.hwfoundationmodel.trackmodel.TimeSequence
    public long acquireTime() {
        return this.e;
    }

    public double c() {
        return this.f14444a;
    }

    public String toString() {
        return this.e + ":" + this.f14444a;
    }
}
