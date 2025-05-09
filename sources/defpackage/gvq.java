package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes4.dex */
public class gvq {
    private ArrayList<koe> b = new ArrayList<>(16);
    private CopyOnWriteArrayList<koe> c = new CopyOnWriteArrayList<>();
    private long d = 0;

    /* renamed from: a, reason: collision with root package name */
    private float f12963a = 0.0f;

    public ArrayList<koe> d(boolean z) {
        ArrayList<koe> arrayList = new ArrayList<>(16);
        if (this.b.size() > 0) {
            arrayList.addAll(this.b);
            if (z) {
                this.b.clear();
            }
        }
        return arrayList;
    }

    public void a(long j, float f) {
        if (j % 5 == 0) {
            float f2 = (f * 10.0f) / 3.6f;
            LogUtil.a("Track_RealTimePaceUtil", "time = ", Long.valueOf(j), " realTimeSpeed = ", Float.valueOf(f2));
            d(j, f2);
            koe koeVar = new koe(j, f2);
            this.b.add(koeVar);
            this.d = j;
            this.f12963a = f2;
            if (this.c.size() >= 12) {
                this.c.remove(0);
                this.c.add(koeVar);
            } else {
                this.c.add(koeVar);
            }
        }
    }

    private void d(long j, float f) {
        long j2 = this.d;
        if (j2 <= 0 || j - j2 <= 5) {
            return;
        }
        LogUtil.a("Track_RealTimePaceUtil", "compensateSpeedData time =", Long.valueOf(j), " currentrealTimeSpeed =", Float.valueOf(f));
        float f2 = this.f12963a;
        long j3 = j - this.d;
        float f3 = (f - f2) / j3;
        long j4 = j3 / 5;
        int i = 0;
        while (i < j4 - 1) {
            long j5 = this.d;
            i++;
            long j6 = i * 5;
            float f4 = this.f12963a + f3;
            if (f4 < 0.0f) {
                return;
            }
            this.b.add(new koe(j5 + j6, f4));
        }
    }

    public CopyOnWriteArrayList<koe> d() {
        if (this.c.size() >= 12) {
            return this.c;
        }
        return null;
    }

    public void b() {
        ArrayList<koe> arrayList = this.b;
        if (arrayList != null) {
            arrayList.clear();
        }
    }
}
