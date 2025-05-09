package defpackage;

import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public class kcr {

    /* renamed from: a, reason: collision with root package name */
    private long f14288a;
    private long b;
    private long c;
    private boolean d;
    private int e;
    private long f;

    public long h() {
        return this.f;
    }

    public long c() {
        return this.c;
    }

    public long d() {
        return this.b;
    }

    public long b() {
        return this.f14288a;
    }

    public int e() {
        return this.e;
    }

    public void b(int i) {
        this.e = i;
    }

    public boolean a() {
        return this.d;
    }

    public void e(boolean z) {
        this.d = z;
    }

    public void e(int i, long j) {
        if (i != 101) {
            if (i != 102) {
                if (i != 301) {
                    if (i != 302) {
                        switch (i) {
                            case 401:
                                this.b = j;
                                break;
                            case 402:
                                this.f14288a = j;
                                break;
                            case 403:
                                this.b = j;
                                this.f14288a = j;
                                break;
                            default:
                                LogUtil.h("MenstrualData setTimeContent error", new Object[0]);
                                break;
                        }
                    }
                }
            }
            this.c = j;
            return;
        }
        this.f = j;
    }
}
