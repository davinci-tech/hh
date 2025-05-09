package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwfoundationmodel.trackmodel.TimeSequence;
import java.io.Serializable;

/* loaded from: classes4.dex */
public class ffn implements Serializable, TimeSequence {
    private static final long serialVersionUID = -5570737047355306335L;

    @SerializedName("cadence")
    private int b;

    @SerializedName("sumCircle")
    private long c;

    @SerializedName("time")
    private long d;

    @SerializedName("deltaCircle")
    private long e;

    public ffn() {
        this.d = -1L;
        this.b = -1;
        this.e = -1L;
        this.c = -1L;
    }

    public ffn(long j, int i) {
        this.e = -1L;
        this.c = -1L;
        this.d = j;
        this.b = i;
    }

    public void c(long j) {
        this.d = j;
    }

    public int e() {
        return this.b;
    }

    public void e(int i) {
        this.b = i;
    }

    @Override // com.huawei.hwfoundationmodel.trackmodel.TimeSequence
    public long acquireTime() {
        return this.d;
    }

    public void b(long j) {
        this.e = j;
    }

    public long b() {
        return this.c;
    }

    public void e(long j) {
        this.c = j;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ffn) && this.d == ((ffn) obj).d;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public void b(int i, double d) {
        if (i == DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_CADENCE.value()) {
            e((int) d);
        } else if (i == DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_DELTA_CIRCLE.value()) {
            b((int) d);
        } else if (i == DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_SUM_CIRCLE.value()) {
            e((int) d);
        }
    }
}
