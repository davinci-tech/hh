package defpackage;

import com.huawei.operation.utils.Constants;
import com.huawei.watchface.utils.constants.WatchFaceConstant;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0016"}, d2 = {"Lcom/huawei/health/trusport_ee/Result;", "", "total", "", "active", "resting", "(FFF)V", "getActive", "()F", "getResting", "getTotal", "component1", "component2", "component3", "copy", "equals", "", "other", WatchFaceConstant.HASHCODE, "", "toString", "", "trusport_ee_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final /* data */ class glj {
    private final float b;
    private final float c;
    private final float d;

    public glj(float f, float f2, float f3) {
        this.c = f;
        this.d = f2;
        this.b = f3;
    }

    /* renamed from: b, reason: from getter */
    public final float getC() {
        return this.c;
    }

    /* renamed from: c, reason: from getter */
    public final float getD() {
        return this.d;
    }

    public String toString() {
        return "Result(total=" + this.c + ", active=" + this.d + ", resting=" + this.b + Constants.RIGHT_BRACKET_ONLY;
    }

    public int hashCode() {
        return (((Float.hashCode(this.c) * 31) + Float.hashCode(this.d)) * 31) + Float.hashCode(this.b);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof glj)) {
            return false;
        }
        glj gljVar = (glj) other;
        return Float.compare(this.c, gljVar.c) == 0 && Float.compare(this.d, gljVar.d) == 0 && Float.compare(this.b, gljVar.b) == 0;
    }
}
