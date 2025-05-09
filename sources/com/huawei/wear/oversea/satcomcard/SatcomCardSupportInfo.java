package com.huawei.wear.oversea.satcomcard;

/* loaded from: classes7.dex */
public class SatcomCardSupportInfo {
    private int c;
    private boolean d;
    private int e;

    /* loaded from: classes9.dex */
    public interface ActivityPeriodConstant {
        public static final int ACTIVITY_PERIOD_FORMAL = 2;
        public static final int ACTIVITY_PERIOD_MASSTEST = 1;
        public static final int ACTIVITY_PERIOD_STOP = 0;
    }

    public boolean c() {
        return this.d;
    }

    public void a(boolean z) {
        this.d = z;
    }

    public int a() {
        return this.e;
    }

    public void d(int i) {
        this.e = i;
    }

    public int e() {
        return this.c;
    }

    public void e(int i) {
        this.c = i;
    }

    public String toString() {
        return "SatcomCardSupportInfo{isSupportSatcomCard=" + this.d + ", activityPeriod=" + this.e + ", returnCode=" + this.c + '}';
    }
}
