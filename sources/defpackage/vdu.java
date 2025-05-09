package defpackage;

import com.huawei.hwdevice.phoneprocess.mgr.exercise.utils.HwWorkoutServiceUtils;
import java.io.Serializable;

/* loaded from: classes7.dex */
public class vdu implements Comparable<vdu>, Serializable {

    /* renamed from: a, reason: collision with root package name */
    public static final vdu f17688a = new vdu(HwWorkoutServiceUtils.HEART_RATE_TRUST_VALUE, 253);
    public static final vdu c = new vdu(HwWorkoutServiceUtils.HEART_RATE_TRUST_VALUE, 255);
    private static final long serialVersionUID = 1;
    private final int d;
    private final int e;

    private vdu(int i, int i2) {
        this.e = i2;
        this.d = i;
    }

    public int b() {
        return this.e;
    }

    public int c() {
        return this.d;
    }

    @Override // java.lang.Comparable
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public int compareTo(vdu vduVar) {
        if (this == vduVar) {
            return 0;
        }
        if (this.d != vduVar.c()) {
            return this.d < vduVar.c() ? 1 : -1;
        }
        if (this.e < vduVar.b()) {
            return 1;
        }
        return this.e > vduVar.b() ? -1 : 0;
    }

    public int hashCode() {
        return ((this.d + 31) * 31) + this.e;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof vdu)) {
            return false;
        }
        vdu vduVar = (vdu) obj;
        return this.d == vduVar.d && this.e == vduVar.e;
    }

    public String toString() {
        return Integer.toString(255 - this.d) + "." + Integer.toString(255 - this.e);
    }

    public static vdu c(int i, int i2) {
        if (i == 254 && i2 == 253) {
            return f17688a;
        }
        if (i == 254 && i2 == 255) {
            return c;
        }
        return new vdu(i, i2);
    }
}
