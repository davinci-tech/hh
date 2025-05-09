package defpackage;

import com.huawei.health.hwhealthtrackalgo.stat.FilterResultListener;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes4.dex */
public class gxw implements FilterResultListener {
    protected boolean c = false;
    private int d = 0;

    /* renamed from: a, reason: collision with root package name */
    private int f12998a = 0;
    private long b = System.currentTimeMillis();

    public void e() {
    }

    @Override // com.huawei.health.hwhealthtrackalgo.stat.FilterResultListener
    public void onFilterResult(int i, long j) {
        if (i == 0) {
            d(j);
        } else {
            a(i, j);
        }
        e();
    }

    private void a(int i, long j) {
        int i2 = this.f12998a;
        if (i2 == 0) {
            this.b = j;
        }
        int i3 = i2 + 1;
        this.f12998a = i3;
        if (i == 1) {
            this.d++;
        }
        long j2 = j - this.b;
        if (j2 > 20000) {
            this.c = (((double) this.d) * 1.0d) / ((double) i3) >= 0.7d;
        }
        if (this.c) {
            LogUtil.a("Track_TrackOverSpeedFilterListener", Long.valueOf(j2), " ", Integer.valueOf(this.f12998a), " ", Integer.valueOf(this.d), " ", Double.valueOf((this.d * 1.0d) / this.f12998a));
        }
    }

    private void d(long j) {
        this.f12998a = 0;
        this.d = 0;
        this.c = false;
        this.b = j;
    }
}
