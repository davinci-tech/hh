package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes3.dex */
public class cbk extends wq implements Comparable<cbk> {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("planId")
    private int f595a;

    public cbk(int i, int i2, int i3, int i4) {
        super(i, i2, i3, null);
        this.f595a = i4;
    }

    public int f() {
        return this.f595a;
    }

    public void d(int i) {
        this.f595a = i;
    }

    @Override // defpackage.wq
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        return (obj instanceof cbk) && ((cbk) obj).b() == b();
    }

    @Override // defpackage.wq
    public int hashCode() {
        return b();
    }

    public String toString() {
        return "BloodPressureAlarmInfo:mPlanId=" + this.f595a + ",alarmId=" + b() + ",hour=" + a() + ",minute=" + e() + ",daysOfWeek=" + d() + ",enabled=" + i();
    }

    @Override // java.lang.Comparable
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public int compareTo(cbk cbkVar) {
        if (cbkVar == null) {
            return 0;
        }
        int a2 = a();
        int a3 = cbkVar.a();
        if (a2 == a3) {
            return e() - cbkVar.e();
        }
        if ((a2 >= 2 && a3 > 2) || (a2 < 2 && a3 < 2)) {
            return a2 - a3;
        }
        if (a2 < 2) {
            return 1;
        }
        return a3 < 2 ? -1 : 0;
    }
}
