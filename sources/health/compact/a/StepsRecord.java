package health.compact.a;

import android.os.Bundle;

/* loaded from: classes.dex */
public class StepsRecord {
    public int g = -1;
    public int d = -1;

    /* renamed from: a, reason: collision with root package name */
    public int f13139a = -1;
    public int b = -1;
    public int i = -1;
    public int c = -1;
    public int j = -1;
    public int e = -1;

    public boolean d(StepsRecord stepsRecord) {
        return stepsRecord != null && this.g == stepsRecord.g && this.d == stepsRecord.d && this.f13139a == stepsRecord.f13139a && this.b == stepsRecord.b;
    }

    public void d() {
        this.g = -1;
        this.c = -1;
        this.d = -1;
        this.f13139a = -1;
        this.b = -1;
        this.i = -1;
        this.j = -1;
        this.e = -1;
    }

    public boolean c() {
        int i = this.g;
        if (i < 0 || this.d < 0 || this.f13139a < 0 || this.b < 0 || this.i < 0) {
            com.huawei.hwlogsmodel.LogUtil.h("steps_StepsRecord", "error data: ", toString());
            return false;
        }
        if (i <= 1000000) {
            return true;
        }
        com.huawei.hwlogsmodel.LogUtil.h("steps_StepsRecord", " the day step is to large,the step :", LogAnonymous.b(i));
        return false;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        StepsRecord stepsRecord = (StepsRecord) obj;
        return this.g == stepsRecord.g && this.d == stepsRecord.d && this.f13139a == stepsRecord.f13139a && this.b == stepsRecord.b && this.j == stepsRecord.j && this.e == stepsRecord.e && this.i == stepsRecord.i;
    }

    public int hashCode() {
        int i = this.g;
        int i2 = this.d;
        int i3 = this.f13139a;
        int i4 = this.b;
        return (((((((((((i * 31) + i2) * 31) + i3) * 31) + i4) * 31) + this.i) * 31) + this.j) * 31) + this.e;
    }

    public void b(StepsRecord stepsRecord) {
        if (stepsRecord != null) {
            this.g = stepsRecord.g;
            this.c = stepsRecord.c;
            this.d = stepsRecord.d;
            this.f13139a = stepsRecord.f13139a;
            this.b = stepsRecord.b;
            this.i = stepsRecord.i;
            this.j = stepsRecord.j;
            this.e = stepsRecord.e;
        }
    }

    public Bundle aaD_() {
        Bundle bundle = new Bundle();
        bundle.putInt("step", this.g);
        bundle.putInt("devicestep", this.c);
        bundle.putInt("distance", this.f13139a);
        bundle.putInt("carior", this.d);
        bundle.putInt("floor", this.b);
        bundle.putInt("target", this.i);
        bundle.putInt("stepTarget", this.i);
        bundle.putInt("intensityTime", this.j);
        bundle.putInt("activeCount", this.e);
        return bundle;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("step ");
        sb.append(this.g);
        sb.append(" devicestep ");
        sb.append(this.c);
        sb.append(" calorie ");
        sb.append(this.d);
        sb.append(" floor ");
        sb.append(this.b);
        sb.append(" distance ");
        sb.append(this.f13139a);
        sb.append(" target ");
        sb.append(this.i);
        sb.append(" intensity ");
        sb.append(this.j);
        sb.append(" activeCount ");
        sb.append(this.e);
        return sb.toString();
    }
}
