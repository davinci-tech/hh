package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.common.utils.CollectionUtils;
import health.compact.a.ReleaseLogUtil;
import java.util.List;

/* loaded from: classes3.dex */
public class ayh {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("enabled")
    private boolean f283a;

    @SerializedName("alarmInfoList")
    private List<wq> e;

    public ayh(boolean z, List<wq> list) {
        b(z);
        d(list);
    }

    public boolean d() {
        return this.f283a;
    }

    public void b(boolean z) {
        this.f283a = z;
        if (CollectionUtils.d(this.e)) {
            ReleaseLogUtil.a("R_HealthLife_HealthModeReminderBean", "setEnabled mAlarmInfoList ", this.e);
            return;
        }
        for (wq wqVar : this.e) {
            if (wqVar != null) {
                wqVar.b(z);
            }
        }
    }

    public List<wq> c() {
        return this.e;
    }

    public void d(List<wq> list) {
        this.e = list;
    }

    public String toString() {
        if (CollectionUtils.d(this.e)) {
            ReleaseLogUtil.a("R_HealthLife_HealthModeReminderBean", "toString mAlarmInfoList ", this.e);
            return "HealthModeReminderBean{mIsEnabled=" + this.f283a + ", mAlarmInfoList=" + this.e + '}';
        }
        StringBuilder sb = new StringBuilder("HealthModeReminderBean{mIsEnabled=" + this.f283a);
        for (wq wqVar : this.e) {
            if (wqVar != null) {
                int a2 = wqVar.a();
                int e = wqVar.e();
                int d = wqVar.d();
                boolean i = wqVar.i();
                sb.append(", {hour=");
                sb.append(a2);
                sb.append(", minute=");
                sb.append(e);
                sb.append(", daysOfWeek=");
                sb.append(d);
                sb.append(", isEnabled=");
                sb.append(i);
                sb.append("}");
            }
        }
        return ((Object) sb) + "}";
    }
}
