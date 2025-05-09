package defpackage;

import android.os.Bundle;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes4.dex */
public class gtr {
    private float j = 0.0f;
    private float d = 0.0f;
    private int c = 0;
    private boolean b = false;

    /* renamed from: a, reason: collision with root package name */
    private boolean f12932a = false;
    private long e = 0;

    public void aTE_(Bundle bundle) {
        int i = bundle.getInt("fitDistance", 0);
        this.c = i != 0 ? i / 100 : 0;
        float f = bundle.getFloat("fitPace", 0.0f) * 3.6f;
        this.j = f;
        this.d = f != 0.0f ? 3600.0f / f : 0.0f;
        this.e = bundle.getLong("fitTimestamp") * 1000;
        LogUtil.c("HiGeoManager", "new distance:" + this.c + ",speed:" + this.j + ",pace:" + this.d);
    }

    public boolean a() {
        LogUtil.c("HiGeoManager", "mHiGeoTime:" + this.e);
        if (this.e == 0) {
            return false;
        }
        long abs = Math.abs(System.currentTimeMillis() - this.e);
        LogUtil.c("HiGeoManager", "diffTime:" + abs);
        return abs > 5000;
    }

    public float c() {
        return this.j;
    }

    public boolean h() {
        return this.b;
    }

    public void c(boolean z) {
        this.b = z;
    }

    public boolean d() {
        return this.f12932a && this.b;
    }

    public int e() {
        return this.c;
    }

    public float b() {
        return this.d;
    }

    public void c(long j) {
        this.e = j;
    }
}
