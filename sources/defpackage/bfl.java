package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class bfl {

    /* renamed from: a, reason: collision with root package name */
    private long f351a;
    private long b;
    private ArrayList<Integer> c = new ArrayList<>(16);

    public void d(long j) {
        this.b = j;
    }

    public long d() {
        return this.b;
    }

    public void a(long j) {
        this.f351a = j;
    }

    public long b() {
        return ((Long) jdy.d(Long.valueOf(this.f351a))).longValue();
    }

    public void a(String str) {
        int length = str.length();
        this.c = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            try {
                this.c.add(i, Integer.valueOf(Integer.parseInt(str.substring(i, i + 1))));
            } catch (NumberFormatException unused) {
                ReleaseLogUtil.c("BTSYNC_CoreSleep_SleepStatusFrame", "setStatusList parseString NumberFormatException");
            }
        }
        LogUtil.a("SleepStatusFrame", "integerArrayList:", Integer.valueOf(this.c.size()));
    }

    public ArrayList<Integer> e() {
        return this.c;
    }

    public String toString() {
        return "SleepStatusFrame{startTime = " + this.b + "endTime = " + this.f351a + "statusList = " + this.c.toString() + "}";
    }
}
