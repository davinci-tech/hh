package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwfoundationmodel.trackmodel.TimeSequence;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class koe implements Serializable, TimeSequence {
    private static final long serialVersionUID = -1986945515238572057L;

    @SerializedName("mTime")
    private long b;

    @SerializedName("mRealTimeSpeed")
    private float c;

    public koe(long j, float f) {
        this.b = j;
        this.c = f;
    }

    public float a() {
        return this.c;
    }

    public float b() {
        return (this.c / 10.0f) * 3.6f;
    }

    public float c() {
        if (Math.abs(this.c) > 1.0E-5d) {
            return 10000.0f / this.c;
        }
        return 0.0f;
    }

    public String toString() {
        return this.b + ":" + this.c;
    }

    public koh d() {
        return new koh(this.b, Math.round(c()));
    }

    public koi e() {
        return new koi(this.b, b());
    }

    @Override // com.huawei.hwfoundationmodel.trackmodel.TimeSequence
    public long acquireTime() {
        return this.b;
    }
}
