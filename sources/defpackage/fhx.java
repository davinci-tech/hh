package defpackage;

import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes4.dex */
public class fhx {

    /* renamed from: a, reason: collision with root package name */
    private int f12520a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int h;

    public int a(int i, boolean z) {
        int i2;
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i != 4) {
                        if (i == 5) {
                            if (z) {
                                return this.b;
                            }
                            return this.c;
                        }
                        LogUtil.b("Suggestion_FitnessHeartRateZoneConfig", "heartRateZone should be given right data ", Integer.valueOf(i));
                        if (i < 1) {
                            return this.d;
                        }
                        return this.c;
                    }
                    if (z) {
                        return this.e;
                    }
                    i2 = this.b;
                } else {
                    if (z) {
                        return this.f12520a;
                    }
                    i2 = this.e;
                }
            } else {
                if (z) {
                    return this.h;
                }
                i2 = this.f12520a;
            }
        } else {
            if (z) {
                return this.d;
            }
            i2 = this.h;
        }
        return i2 - 1;
    }

    public int e() {
        return this.c;
    }

    public void e(int i) {
        this.c = i;
    }

    public void a(int i) {
        this.b = i;
    }

    public void c(int i) {
        this.e = i;
    }

    public void b(int i) {
        this.f12520a = i;
    }

    public void f(int i) {
        this.h = i;
    }

    public void d(int i) {
        this.d = i;
    }
}
