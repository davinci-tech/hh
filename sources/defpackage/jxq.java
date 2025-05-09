package defpackage;

import com.huawei.health.devicemgr.business.entity.DeviceInfo;

/* loaded from: classes5.dex */
public class jxq {
    private DeviceInfo c;
    private long e;
    private int j;
    private int h = 0;
    private int b = 0;

    /* renamed from: a, reason: collision with root package name */
    private int f14173a = 0;
    private int d = 0;

    public int f() {
        return this.j;
    }

    public void g(int i) {
        this.j = i;
    }

    public int a() {
        return ((Integer) jdy.d(Integer.valueOf(this.b))).intValue();
    }

    public void i(int i) {
        this.b = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public void a(int i) {
        this.b += i;
    }

    public int c() {
        return ((Integer) jdy.d(Integer.valueOf(this.f14173a))).intValue();
    }

    public void b(int i) {
        this.f14173a = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public void e(int i) {
        this.f14173a += i;
    }

    public int b() {
        return ((Integer) jdy.d(Integer.valueOf(this.d))).intValue();
    }

    public void d(int i) {
        this.d = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public void c(int i) {
        this.d += i;
    }

    public long e() {
        return ((Long) jdy.d(Long.valueOf(this.e))).longValue();
    }

    public void a(long j) {
        this.e = ((Long) jdy.d(Long.valueOf(j))).longValue();
    }

    public DeviceInfo d() {
        return this.c;
    }

    public void a(DeviceInfo deviceInfo) {
        this.c = deviceInfo;
    }

    public String toString() {
        return "LastTotalValue{sportType=" + this.h + ", lastTotalSteps=" + this.b + ", lastTotalCalories=" + this.f14173a + ", lastTotalDistance=" + this.d + ", lastTimeStamp=" + this.e + '}';
    }
}
